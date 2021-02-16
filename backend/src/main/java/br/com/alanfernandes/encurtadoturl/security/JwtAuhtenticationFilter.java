package br.com.alanfernandes.encurtadoturl.security;

import br.com.alanfernandes.encurtadoturl.CustomJwtConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtAuhtenticationFilter extends BasicAuthenticationFilter {

    private CustomUserDetailService customUserDetailService;

    private CustomJwtConfig customJwtConfig;

    public JwtAuhtenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailService customUserDetailService,
                                   CustomJwtConfig customJwtConfig) {
        super(authenticationManager);
        this.customUserDetailService = customUserDetailService;
        this.customJwtConfig = customJwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(CustomJwtConfig.HEADER_STRING);
        if (header == null || !header.startsWith(CustomJwtConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String header = request.getHeader(CustomJwtConfig.HEADER_STRING);
        if (header == null) return null;
        String token = header.substring(7);
        Algorithm algorithmHS = Algorithm.HMAC256(customJwtConfig.getJwtKey());
        Verification verifier = JWT.require(algorithmHS);
        DecodedJWT decode = JWT.decode(token);
        Map<String, Claim> claims = decode.getClaims();
        Claim claim = claims.get("username");
        String username = claim.as(String.class);
        try {
            UserDetails user = customUserDetailService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }catch (UsernameNotFoundException e){
            return null;
        }
    }
}

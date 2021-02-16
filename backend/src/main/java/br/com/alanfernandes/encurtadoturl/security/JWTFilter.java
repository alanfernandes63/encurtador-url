package br.com.alanfernandes.encurtadoturl.security;

import br.com.alanfernandes.encurtadoturl.CustomJwtConfig;
import br.com.alanfernandes.encurtadoturl.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    AuthenticationManager autenticationManager;

    @Autowired
    CustomJwtConfig customJwtConfig;

    public JWTFilter(AuthenticationManager autenticationManager, CustomJwtConfig customJwtConfig) {
        this.autenticationManager = autenticationManager;
        this.customJwtConfig = customJwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return this.autenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getUsername(), usuario.getPassword()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Algorithm algorithmHS = Algorithm.HMAC256(customJwtConfig.getJwtKey());
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();
        String token = JWT.create()
                .withClaim("username", username)
                .sign(algorithmHS);
        response.addHeader(CustomJwtConfig.HEADER_STRING, CustomJwtConfig.TOKEN_PREFIX + token);
    }


}

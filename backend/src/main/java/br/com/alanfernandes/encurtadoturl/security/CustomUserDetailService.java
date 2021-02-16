package br.com.alanfernandes.encurtadoturl.security;

import br.com.alanfernandes.encurtadoturl.entities.Role;
import br.com.alanfernandes.encurtadoturl.entities.Usuario;
import br.com.alanfernandes.encurtadoturl.repository.RoleRepository;
import br.com.alanfernandes.encurtadoturl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("usuário nao encontrado"));
        List<Role> rolesUser = roleRepository.findByUsuario(usuario);
        usuario.setRoles(rolesUser);
        return new UserSecurity(usuario);
    }
}

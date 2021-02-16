package br.com.alanfernandes.encurtadoturl.service;

import br.com.alanfernandes.encurtadoturl.AlreadExistUserException;
import br.com.alanfernandes.encurtadoturl.entities.Role;
import br.com.alanfernandes.encurtadoturl.entities.Usuario;
import br.com.alanfernandes.encurtadoturl.exceptions.UserNotFoundEception;
import br.com.alanfernandes.encurtadoturl.repository.RoleRepository;
import br.com.alanfernandes.encurtadoturl.repository.UsuarioRepository;
import br.com.alanfernandes.encurtadoturl.security.Roles;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    RoleRepository roleRepository;

    public Usuario add(Usuario usuario) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioOptional.isPresent()) {
            throw new AlreadExistUserException("Usuário com username " + usuario.getUsername() + " já existe");
        }

        String passwordEncoded = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(passwordEncoded);

        usuarioRepository.save(usuario);

        Role role = new Role();
        role.setNome(Roles.USER.getLabel());
        role.setUsuario(usuario);
        roleRepository.save(role);


        return usuario;
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.orElseThrow(() -> new UserNotFoundEception("Usuário não encontrado"));
        return usuarioOptional.get();
    }

}

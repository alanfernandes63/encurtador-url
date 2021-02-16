package br.com.alanfernandes.encurtadoturl.repository;

import br.com.alanfernandes.encurtadoturl.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}

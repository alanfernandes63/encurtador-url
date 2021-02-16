package br.com.alanfernandes.encurtadoturl.repository;

import br.com.alanfernandes.encurtadoturl.entities.Role;
import br.com.alanfernandes.encurtadoturl.entities.Url;
import br.com.alanfernandes.encurtadoturl.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByCodigo(String codigo);

    List<Url> findByUsuario(Usuario usuario);
}

package br.com.alanfernandes.encurtadoturl.controller;

import br.com.alanfernandes.encurtadoturl.AlreadExistUserException;
import br.com.alanfernandes.encurtadoturl.dto.MakeResponse;
import br.com.alanfernandes.encurtadoturl.entities.Usuario;
import br.com.alanfernandes.encurtadoturl.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/usuario")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController {

    UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<MakeResponse<Usuario>> add(@RequestBody Usuario usuario) {
        Usuario usuarioResponse = null;
        try {
            usuarioResponse = usuarioService.add(usuario);
            return new ResponseEntity<MakeResponse<Usuario>>(new MakeResponse("cadastrado com sucesso", usuario), HttpStatus.CREATED);
        } catch (AlreadExistUserException e) {
            return new ResponseEntity<MakeResponse<Usuario>>(new MakeResponse<Usuario>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}

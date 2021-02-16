package br.com.alanfernandes.encurtadoturl.controller;

import br.com.alanfernandes.encurtadoturl.dto.MakeResponse;
import br.com.alanfernandes.encurtadoturl.dto.UrlRequest;
import br.com.alanfernandes.encurtadoturl.entities.Url;
import br.com.alanfernandes.encurtadoturl.exceptions.UserNotFoundEception;
import br.com.alanfernandes.encurtadoturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/url")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UrlController {

    @Autowired
    UrlService urlService;

    @Autowired
    private Environment env;

    @PostMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<MakeResponse<Url>> add(@RequestBody UrlRequest url, @PathVariable Long idUsuario) {
        Url newUrl = null;
        try {
            newUrl = urlService.add(url, idUsuario);
            return new ResponseEntity<MakeResponse<Url>>(new MakeResponse<Url>("cadastrado comsucesso!", newUrl), HttpStatus.CREATED);
        } catch (UserNotFoundEception e) {
            return new ResponseEntity<MakeResponse<Url>>(new MakeResponse<Url>("Usuário não cadastrado", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<MakeResponse<List<Url>>> listarPorUsuario(@PathVariable Long idUsuario) {

        try {
            List<Url> urls = urlService.listarPorUsuario(idUsuario);
            return new ResponseEntity<MakeResponse<List<Url>>>(new MakeResponse<List<Url>>("listado com sucesso", urls), HttpStatus.OK);
        } catch (UserNotFoundEception e) {
            return new ResponseEntity<MakeResponse<List<Url>>>(new MakeResponse<List<Url>>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}

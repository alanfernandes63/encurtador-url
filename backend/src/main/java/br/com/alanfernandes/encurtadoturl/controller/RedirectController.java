package br.com.alanfernandes.encurtadoturl.controller;

import br.com.alanfernandes.encurtadoturl.entities.Url;
import br.com.alanfernandes.encurtadoturl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping
public class RedirectController {

    @Autowired
    UrlRepository urlRepository;

    @GetMapping("{codigo}")
    public String redirecionar(HttpServletRequest request, HttpServletResponse response, @PathVariable String codigo) {
        try {
            Optional<Url> optionalUrl = urlRepository.findByCodigo(codigo);
            if(optionalUrl.isPresent()){
                Url url = optionalUrl.get();
                response.sendRedirect(url.getUrlOriginal());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

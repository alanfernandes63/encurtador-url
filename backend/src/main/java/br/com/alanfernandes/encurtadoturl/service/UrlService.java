package br.com.alanfernandes.encurtadoturl.service;

import br.com.alanfernandes.encurtadoturl.config.Urls;
import br.com.alanfernandes.encurtadoturl.dto.UrlRequest;
import br.com.alanfernandes.encurtadoturl.entities.Url;
import br.com.alanfernandes.encurtadoturl.entities.Usuario;
import br.com.alanfernandes.encurtadoturl.exceptions.UrlInvalidException;
import br.com.alanfernandes.encurtadoturl.repository.UrlRepository;
import br.com.alanfernandes.encurtadoturl.repository.UsuarioRepository;
import br.com.alanfernandes.encurtadoturl.util.Patterns;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UrlService {

    private UrlRepository urlRepository;

    private UsuarioService usuarioService;

    public Url add(UrlRequest urlRequest, Long idUsuario) {

        String url = urlRequest.getValue();
        Url newUrl = new Url();

        Usuario usuario = usuarioService.findById(idUsuario);

        if (validarUrl(url)) {
            String codigo = gerarCodigo();
            String urlCurta = Urls.BASE_URL + codigo;
            newUrl.setUrlOriginal(url);
            newUrl.setUrlCurta(urlCurta);
            newUrl.setCodigo(codigo);
            newUrl.setLocalDate(LocalDate.now());
            newUrl.setUsuario(usuario);

            urlRepository.save(newUrl);

        } else {
            throw new UrlInvalidException("Url inválida");
        }
        return newUrl;
    }

    public List<Url> listarPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario);
        return urlRepository.findByUsuario(usuario);
    }

    private Boolean validarUrl(String url) {
        String a = Patterns.URL;
        Pattern pattern = Pattern.compile(a);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }

    private String gerarCodigo() {
        String codigo = Long.toString(System.currentTimeMillis());
        Integer randon = ThreadLocalRandom.current().nextInt(0, 100);
        return codigo.concat(randon.toString());
    }
}

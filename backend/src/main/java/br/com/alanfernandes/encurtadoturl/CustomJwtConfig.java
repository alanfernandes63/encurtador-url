package br.com.alanfernandes.encurtadoturl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtConfig {

    public final String KEY = "spring.jwt.key";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/v1/usuario/cadastro";

    @Autowired
    Environment environment;

    public String getJwtKey() {
        return this.environment.getProperty(this.KEY);
    }
}
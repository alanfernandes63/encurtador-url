package br.com.alanfernandes.encurtadoturl.config;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class PasswordEncoder {

    public static String encode(String password) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
                .encode(password);
    }

}

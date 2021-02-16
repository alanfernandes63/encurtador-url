package br.com.alanfernandes.encurtadoturl.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Roles {
    USER("ROLE_USER");
    String label;
}

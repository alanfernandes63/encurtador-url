package br.com.alanfernandes.encurtadoturl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MakeResponse<T> {

    String message;

    T payload;
}

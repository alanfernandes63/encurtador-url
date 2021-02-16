package br.com.alanfernandes.encurtadoturl.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "urls", uniqueConstraints = @UniqueConstraint(columnNames = {"urlCurta"}))
public class Url extends BaseEntity {

    @NotNull
    @NotBlank
    private String UrlOriginal;

    @NotNull
    @NotBlank
    private String urlCurta;

    @NotNull
    private LocalDate localDate;

    private String codigo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;
}

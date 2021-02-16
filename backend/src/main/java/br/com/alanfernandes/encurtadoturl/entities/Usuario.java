package br.com.alanfernandes.encurtadoturl.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class Usuario extends BaseEntity {

    private String username;

    private String password;

    private Boolean enabled;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    List<Role> roles;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Url> urls;

}

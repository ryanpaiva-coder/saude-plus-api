package br.com.projeto.saude_plus.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Medic extends User{

    @NotNull
    @Size(min = 5, max = 20, message = "O CRM deve ter entre 5 e 20 caracteres.")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "O CRM deve conter apenas letras e números.")
    @Column(unique = true)
    private String crm;

}

package br.com.projeto.saude_plus.api.dto.roleDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleInputDTO {

    @NotBlank
    @Size(max = 32)
    private String nome;
}

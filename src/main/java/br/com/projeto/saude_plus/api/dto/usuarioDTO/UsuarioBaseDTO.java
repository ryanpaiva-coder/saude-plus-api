package br.com.projeto.saude_plus.api.dto.usuarioDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioBaseDTO {

    @NotNull
    private Long id;

}

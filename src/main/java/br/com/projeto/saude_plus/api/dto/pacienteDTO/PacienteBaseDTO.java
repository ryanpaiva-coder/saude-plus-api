package br.com.projeto.saude_plus.api.dto.pacienteDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteBaseDTO {

    @NotNull
    private Long id;

}

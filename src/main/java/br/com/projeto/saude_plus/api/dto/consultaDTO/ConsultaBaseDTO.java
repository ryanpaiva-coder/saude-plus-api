package br.com.projeto.saude_plus.api.dto.consultaDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaBaseDTO {

    @NotNull
    private Long id;

}

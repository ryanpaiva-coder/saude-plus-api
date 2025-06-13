package br.com.projeto.saude_plus.api.dto.consultaDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JustificativaCancelamentoDTO {

    @NotBlank
    private String justificativa;
    
}

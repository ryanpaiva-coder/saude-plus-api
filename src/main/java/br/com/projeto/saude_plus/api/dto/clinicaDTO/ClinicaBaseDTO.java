package br.com.projeto.saude_plus.api.dto.clinicaDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicaBaseDTO {

    @NotNull
    private Long id;

}

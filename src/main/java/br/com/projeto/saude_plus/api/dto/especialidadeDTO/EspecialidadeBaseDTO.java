package br.com.projeto.saude_plus.api.dto.especialidadeDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadeBaseDTO {

    @NotNull
    private Long id;

}

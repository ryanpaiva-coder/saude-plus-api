package br.com.projeto.saude_plus.api.dto.gerenteDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GerenteBaseDTO {

    @NotNull
    private Long id;
}

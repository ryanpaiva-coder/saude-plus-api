package br.com.projeto.saude_plus.api.dto.enderecoDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoBaseDTO {

    @NotNull
    private Long id;

}

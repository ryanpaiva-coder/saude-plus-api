package br.com.projeto.saude_plus.api.dto.especialidadeDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadeInputDTO {

    @NotBlank
    @Size(max = 50)
    private String nome;
    
}

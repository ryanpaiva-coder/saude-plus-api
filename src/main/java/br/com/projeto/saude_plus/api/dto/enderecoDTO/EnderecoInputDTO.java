package br.com.projeto.saude_plus.api.dto.enderecoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDTO {

    @NotBlank
    @Size(max = 100)
    private String logradouro;

    @NotBlank
    @Size(max = 10)
    private String numero;

    @NotBlank
    @Size(max = 50)
    private String bairro;

    @NotBlank
    @Size(max = 8)
    private String cep;

    @NotBlank
    @Size(max = 50)
    private String cidade;

    @NotBlank
    @Size(max = 2)
    private String estado;
    
}

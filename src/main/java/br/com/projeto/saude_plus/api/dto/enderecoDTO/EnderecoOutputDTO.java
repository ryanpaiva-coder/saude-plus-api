package br.com.projeto.saude_plus.api.dto.enderecoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoOutputDTO {

    private Long id;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;
    
}

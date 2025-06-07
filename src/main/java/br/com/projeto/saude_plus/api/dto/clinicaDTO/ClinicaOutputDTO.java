package br.com.projeto.saude_plus.api.dto.clinicaDTO;

import br.com.projeto.saude_plus.api.dto.enderecoDTO.EnderecoOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicaOutputDTO {

    private Long id;

    private String cnpjClinica;

    private String nomeFantasia;

    private String telefone;
    
    private EnderecoOutputDTO endereco;
}

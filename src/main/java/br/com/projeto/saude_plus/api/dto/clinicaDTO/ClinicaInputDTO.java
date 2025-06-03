package br.com.projeto.saude_plus.api.dto.clinicaDTO;

import br.com.projeto.saude_plus.api.dto.enderecoDTO.EnderecoInputDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicaInputDTO {

    @NotBlank
    @Size(max = 14)
    private String cnpjClinica;

    @NotBlank
    @Size(max = 100)
    private String nomeFantasia;

    @Size(max = 11)
    private String telefone;

    @NotNull
    private EnderecoInputDTO endereco;
}

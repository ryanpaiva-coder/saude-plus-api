package br.com.projeto.saude_plus.api.dto.usuarioDTO;

import java.time.LocalDate;

import br.com.projeto.saude_plus.api.dto.enderecoDTO.EnderecoInputDTO;
import br.com.projeto.saude_plus.domain.validation.CPF;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDTO {

    @NotBlank
    private String nome;

    @CPF
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    private String senha;

    @NotBlank
    @Size(min = 1, max = 1)
    private String sexo;

    @Size(max = 11)
    private String telefone;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private EnderecoInputDTO endereco;
}

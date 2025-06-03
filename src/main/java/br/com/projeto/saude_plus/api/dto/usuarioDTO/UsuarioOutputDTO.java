package br.com.projeto.saude_plus.api.dto.usuarioDTO;

import br.com.projeto.saude_plus.api.dto.enderecoDTO.EnderecoOutputDTO;
import br.com.projeto.saude_plus.api.dto.roleDTO.RoleOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutputDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String sexo;

    private String telefone;

    private String dataNascimento;

    private Boolean ativo;

    private RoleOutputDTO role;

    private EnderecoOutputDTO endereco;
    
}

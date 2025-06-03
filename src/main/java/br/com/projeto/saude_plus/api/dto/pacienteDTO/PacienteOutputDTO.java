package br.com.projeto.saude_plus.api.dto.pacienteDTO;

import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteOutputDTO extends UsuarioOutputDTO {

    private String descricao;
    
}

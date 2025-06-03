package br.com.projeto.saude_plus.api.dto.pacienteDTO;

import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioInputDTO;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteInputDTO extends UsuarioInputDTO {

    @Size(max = 1000)
    private String descricao;

}

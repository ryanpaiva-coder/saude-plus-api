package br.com.projeto.saude_plus.api.dto.medicoDTO;

import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioInputDTO;
import br.com.projeto.saude_plus.domain.model.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoInputDTO extends UsuarioInputDTO {

    @NotBlank
    @Size(max = 12)
    private String crm;

    @NotNull
    private Especialidade especialidade;
}

package br.com.projeto.saude_plus.api.dto.medicoDTO;

import br.com.projeto.saude_plus.api.dto.clinicaDTO.ClinicaOutputDTO;
import br.com.projeto.saude_plus.api.dto.especialidadeDTO.EspecialidadeOutputDTO;
import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoOutputDTO extends UsuarioOutputDTO {

    private String crm;

    private Long id;

    private ClinicaOutputDTO clinica;

    private EspecialidadeOutputDTO especialidade;
    
}
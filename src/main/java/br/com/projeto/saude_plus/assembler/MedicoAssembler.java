package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoInputDTO;
import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoOutputDTO;
import br.com.projeto.saude_plus.domain.model.Medico;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MedicoAssembler {

    private ModelMapper modelMapper;

    public MedicoOutputDTO toOutputDTO(Medico medico) {
        return modelMapper.map(medico, MedicoOutputDTO.class);
    }

    public Medico toEntity(MedicoInputDTO medicoInputDTO) {
        return modelMapper.map(medicoInputDTO, Medico.class);
    }
}

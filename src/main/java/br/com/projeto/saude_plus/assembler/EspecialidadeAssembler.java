package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.especialidadeDTO.EspecialidadeInputDTO;
import br.com.projeto.saude_plus.api.dto.especialidadeDTO.EspecialidadeOutputDTO;
import br.com.projeto.saude_plus.domain.model.Especialidade;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EspecialidadeAssembler {

    private ModelMapper modelMapper;

    public EspecialidadeOutputDTO toOutputDTO(Especialidade especialidade) {
        return modelMapper.map(especialidade, EspecialidadeOutputDTO.class);
    }

    public Especialidade toEntity(EspecialidadeInputDTO especialidadeInputDTO) {
        return modelMapper.map(especialidadeInputDTO, Especialidade.class);
    }
}

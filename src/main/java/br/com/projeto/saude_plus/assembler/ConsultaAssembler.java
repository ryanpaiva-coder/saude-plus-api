package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.consultaDTO.ConsultaInputDTO;
import br.com.projeto.saude_plus.api.dto.consultaDTO.ConsultaOutputDTO;
import br.com.projeto.saude_plus.domain.model.Consulta;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ConsultaAssembler {

    private ModelMapper modelMapper;

    public ConsultaOutputDTO toOutputDTO(Consulta consulta) {
        return modelMapper.map(consulta, ConsultaOutputDTO.class);
    }

    public Consulta toEntity(ConsultaInputDTO consultaInputDTO) {
        return modelMapper.map(consultaInputDTO, Consulta.class);
    }
}

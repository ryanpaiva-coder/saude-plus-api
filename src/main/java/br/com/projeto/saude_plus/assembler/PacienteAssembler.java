package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.pacienteDTO.PacienteInputDTO;
import br.com.projeto.saude_plus.api.dto.pacienteDTO.PacienteOutputDTO;
import br.com.projeto.saude_plus.domain.model.Paciente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PacienteAssembler {

    private ModelMapper modelMapper;

    public PacienteOutputDTO toOutputDTO(Paciente paciente) {
        return modelMapper.map(paciente, PacienteOutputDTO.class);
    }

    public Paciente toEntity(PacienteInputDTO pacienteInputDTO) {
        return modelMapper.map(pacienteInputDTO, Paciente.class);
    }
}

package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.clinicaDTO.ClinicaInputDTO;
import br.com.projeto.saude_plus.api.dto.clinicaDTO.ClinicaOutputDTO;
import br.com.projeto.saude_plus.domain.model.Clinica;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClinicaAssembler {

    private ModelMapper modelMapper;

    public ClinicaOutputDTO toOutputDTO(Clinica clinica) {
        return modelMapper.map(clinica, ClinicaOutputDTO.class);
    }

    public Clinica toEntity(ClinicaInputDTO clinicaInputDTO) {
        return modelMapper.map(clinicaInputDTO, Clinica.class);
    }
}
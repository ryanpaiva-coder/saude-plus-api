package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.gerenteDTO.GerenteInputDTO;
import br.com.projeto.saude_plus.api.dto.gerenteDTO.GerenteOutputDTO;
import br.com.projeto.saude_plus.domain.model.Gerente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GerenteAssembler {

    private ModelMapper modelMapper;

    public GerenteOutputDTO toOutputDTO(Gerente gerente) {
        return modelMapper.map(gerente, GerenteOutputDTO.class);
    }

    public Gerente toEntity(GerenteInputDTO gerenteInputDTO) {
        return modelMapper.map(gerenteInputDTO, Gerente.class);
    }
}

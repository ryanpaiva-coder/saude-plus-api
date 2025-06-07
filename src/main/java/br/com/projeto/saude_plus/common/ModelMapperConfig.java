package br.com.projeto.saude_plus.common;

import br.com.projeto.saude_plus.api.dto.consultaDTO.ConsultaInputDTO;
import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoInputDTO;
import br.com.projeto.saude_plus.domain.model.Consulta;
import br.com.projeto.saude_plus.domain.model.Medico;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(MedicoInputDTO.class, Medico.class)
                .addMappings(mapper -> mapper.skip(Medico::setId));

        modelMapper.typeMap(ConsultaInputDTO.class, Consulta.class)
                .addMappings(mapper -> mapper.skip(Consulta::setId));

        return modelMapper;
    }
}

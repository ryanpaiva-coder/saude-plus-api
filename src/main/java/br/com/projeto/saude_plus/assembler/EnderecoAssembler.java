package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.enderecoDTO.EnderecoInputDTO;
import br.com.projeto.saude_plus.api.dto.enderecoDTO.EnderecoOutputDTO;
import br.com.projeto.saude_plus.domain.model.Endereco;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EnderecoAssembler {

    private ModelMapper modelMapper;

    public EnderecoOutputDTO toOutputDTO(Endereco endereco) {
        return modelMapper.map(endereco, EnderecoOutputDTO.class);
    }

    public Endereco toEntity(EnderecoInputDTO enderecoInputDTO) {
        return modelMapper.map(enderecoInputDTO, Endereco.class);
    }
}

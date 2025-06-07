package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioInputDTO;
import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioOutputDTO;
import br.com.projeto.saude_plus.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsuarioAssembler {

    private ModelMapper modelMapper;

    public UsuarioOutputDTO toOutputDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioOutputDTO.class);
    }

    public Usuario toEntity(UsuarioInputDTO usuarioInputDTO) {
        return modelMapper.map(usuarioInputDTO, Usuario.class);
    }
}

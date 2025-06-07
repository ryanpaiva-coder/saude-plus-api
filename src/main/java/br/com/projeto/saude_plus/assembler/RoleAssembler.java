package br.com.projeto.saude_plus.assembler;

import br.com.projeto.saude_plus.api.dto.roleDTO.RoleInputDTO;
import br.com.projeto.saude_plus.api.dto.roleDTO.RoleOutputDTO;
import br.com.projeto.saude_plus.domain.model.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RoleAssembler {

    private ModelMapper modelMapper;

    public RoleOutputDTO toOutputDTO(Role role) {
        return modelMapper.map(role, RoleOutputDTO.class);
    }

    public Role toEntity(RoleInputDTO roleInputDTO) {
        return modelMapper.map(roleInputDTO, Role.class);
    }
}

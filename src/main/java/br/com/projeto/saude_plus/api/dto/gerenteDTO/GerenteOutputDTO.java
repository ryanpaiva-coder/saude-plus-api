package br.com.projeto.saude_plus.api.dto.gerenteDTO;

import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GerenteOutputDTO extends UsuarioOutputDTO {

    private Long id;

    private String codigoAutorizacao;

    private LocalDate dataInicioGestao;
}

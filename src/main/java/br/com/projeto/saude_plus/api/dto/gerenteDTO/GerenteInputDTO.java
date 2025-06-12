package br.com.projeto.saude_plus.api.dto.gerenteDTO;

import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioInputDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GerenteInputDTO extends UsuarioInputDTO {

    @NotBlank
    private String codigoAutorizacao;

    @NotNull
    private LocalDate dataInicioGestao;
}

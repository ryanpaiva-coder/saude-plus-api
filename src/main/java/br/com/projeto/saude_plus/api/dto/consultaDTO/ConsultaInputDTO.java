package br.com.projeto.saude_plus.api.dto.consultaDTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.projeto.saude_plus.domain.model.Medico;
import br.com.projeto.saude_plus.domain.model.Paciente;

@Getter
@Setter
public class ConsultaInputDTO {

    @NotNull
    private LocalDateTime inicio;

    @DecimalMin("0.0")
    private BigDecimal valor;

    private String diagnostico;

    private String observacao;

    @NotNull
    private Medico medico;

    @NotNull
    private Paciente paciente;

}
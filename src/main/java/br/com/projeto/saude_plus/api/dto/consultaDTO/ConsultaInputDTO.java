package br.com.projeto.saude_plus.api.dto.consultaDTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConsultaInputDTO {

    @NotNull
    private String inicio;

    @DecimalMin("0.0")
    private BigDecimal valor;

    private String diagnostico;

    private String observacao;

    @NotNull
    private Long idMedico;

    @NotNull
    private Long idPaciente;

}
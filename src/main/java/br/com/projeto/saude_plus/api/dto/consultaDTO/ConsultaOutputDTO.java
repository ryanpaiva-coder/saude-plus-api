package br.com.projeto.saude_plus.api.dto.consultaDTO;

import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoOutputDTO;
import br.com.projeto.saude_plus.api.dto.pacienteDTO.PacienteOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConsultaOutputDTO {

    private Long id;

    private String inicio;

    private BigDecimal valor;

    private String diagnostico;

    private String observacao;

    private String status;

    private MedicoOutputDTO medico;
    
    private PacienteOutputDTO paciente;
}

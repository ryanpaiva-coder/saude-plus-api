package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.consultaDTO.ConsultaInputDTO;
import br.com.projeto.saude_plus.api.dto.consultaDTO.ConsultaOutputDTO;
import br.com.projeto.saude_plus.api.dto.consultaDTO.JustificativaCancelamentoDTO;
import br.com.projeto.saude_plus.assembler.ConsultaAssembler;
import br.com.projeto.saude_plus.domain.enums.StatusConsulta;
import br.com.projeto.saude_plus.domain.model.Consulta;
import br.com.projeto.saude_plus.domain.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaAssembler consultaAssembler;

    @PostMapping
    public ResponseEntity<ConsultaOutputDTO> agendarConsulta(
            @Valid @RequestBody ConsultaInputDTO consultaInputDTO) {
        Consulta consulta = consultaAssembler.toEntity(consultaInputDTO);
        Consulta novaConsulta = consultaService.agendarConsulta(consulta);
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(novaConsulta));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaOutputDTO>> listarTodas() {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarTodas()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaOutputDTO> buscarPorId(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(consulta));
    }

    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<ConsultaOutputDTO>> listarPorMedico(@PathVariable Long idMedico) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarPorMedico(idMedico)));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<ConsultaOutputDTO>> listarPorPaciente(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarPorPaciente(idPaciente)));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultaOutputDTO>> listarPorStatus(@PathVariable StatusConsulta status) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarPorStatus(status)));
    }

    @PutMapping("/{id}/desmarcar")
    public ResponseEntity<ConsultaOutputDTO> desmarcarConsulta(@PathVariable Long id) {
        Consulta consulta = consultaService.desmarcarConsulta(id);
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(consulta));
    }

    @PutMapping("/{id}/cancelar-medico")
    public ResponseEntity<ConsultaOutputDTO> cancelarConsultaPorMedico(
            @PathVariable Long id,
            @Valid @RequestBody JustificativaCancelamentoDTO dto) {
        Consulta consulta = consultaService.cancelarConsultaPorMedico(id, dto.getJustificativa());
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(consulta));
    }

    @GetMapping("/medico/{idMedico}/futuras")
    public ResponseEntity<List<ConsultaOutputDTO>> listarConsultasFuturasMedico(@PathVariable Long idMedico) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarConsultasFuturasMedico(idMedico)));
    }

    @GetMapping("/paciente/{idPaciente}/futuras")
    public ResponseEntity<List<ConsultaOutputDTO>> listarConsultasFuturasPaciente(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarConsultasFuturasPaciente(idPaciente)));
    }

    private List<ConsultaOutputDTO> mapToOutputDTOList(List<Consulta> consultas) {
        return consultas.stream()
                .map(consultaAssembler::toOutputDTO)
                .collect(Collectors.toList());
    }
}

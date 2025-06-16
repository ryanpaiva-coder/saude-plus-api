package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.consultaDTO.ConsultaInputDTO;
import br.com.projeto.saude_plus.api.dto.consultaDTO.ConsultaOutputDTO;
import br.com.projeto.saude_plus.api.dto.consultaDTO.JustificativaCancelamentoDTO;
import br.com.projeto.saude_plus.assembler.ConsultaAssembler;
import br.com.projeto.saude_plus.domain.enums.StatusConsulta;
import br.com.projeto.saude_plus.domain.model.Consulta;
import br.com.projeto.saude_plus.domain.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Consultas", description = "Operações relacionadas a consultas médicas")
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaAssembler consultaAssembler;

    @Operation(summary = "Agendar nova consulta", description = "Agenda uma nova consulta médica")
    @PostMapping
    public ResponseEntity<ConsultaOutputDTO> agendarConsulta(
            @Valid @RequestBody ConsultaInputDTO consultaInputDTO) {
        Consulta consulta = consultaAssembler.toEntity(consultaInputDTO);
        Consulta novaConsulta = consultaService.agendarConsulta(consulta);
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(novaConsulta));
    }

    @Operation(summary = "Listar todas as consultas", description = "Retorna uma lista com todas as consultas")
    @GetMapping
    public ResponseEntity<List<ConsultaOutputDTO>> listarTodas() {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarTodas()));
    }

    @Operation(summary = "Buscar consulta por ID", description = "Retorna os dados de uma consulta pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaOutputDTO> buscarPorId(
            @Parameter(description = "ID da consulta") @PathVariable Long id) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(consulta));
    }

    @Operation(summary = "Listar consultas por médico", description = "Retorna uma lista de consultas de um médico")
    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<ConsultaOutputDTO>> listarPorMedico(
            @Parameter(description = "ID do médico") @PathVariable Long idMedico) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarPorMedico(idMedico)));
    }

    @Operation(summary = "Listar consultas por paciente", description = "Retorna uma lista de consultas de um paciente")
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<ConsultaOutputDTO>> listarPorPaciente(
            @Parameter(description = "ID do paciente") @PathVariable Long idPaciente) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarPorPaciente(idPaciente)));
    }

    @Operation(summary = "Listar consultas por status", description = "Retorna uma lista de consultas filtradas pelo status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultaOutputDTO>> listarPorStatus(
            @Parameter(description = "Status da consulta") @PathVariable StatusConsulta status) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarPorStatus(status)));
    }

    @Operation(summary = "Desmarcar consulta", description = "Desmarca uma consulta pelo ID")
    @PutMapping("/{id}/desmarcar")
    public ResponseEntity<ConsultaOutputDTO> desmarcarConsulta(
            @Parameter(description = "ID da consulta") @PathVariable Long id) {
        Consulta consulta = consultaService.desmarcarConsulta(id);
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(consulta));
    }

    @Operation(summary = "Cancelar consulta por médico", description = "Cancela uma consulta pelo médico, informando justificativa")
    @PutMapping("/{id}/cancelar-medico")
    public ResponseEntity<ConsultaOutputDTO> cancelarConsultaPorMedico(
            @Parameter(description = "ID da consulta") @PathVariable Long id,
            @Valid @RequestBody JustificativaCancelamentoDTO dto) {
        Consulta consulta = consultaService.cancelarConsultaPorMedico(id, dto.getJustificativa());
        return ResponseEntity.ok(consultaAssembler.toOutputDTO(consulta));
    }

    @Operation(summary = "Listar consultas futuras de um médico", description = "Retorna uma lista de consultas futuras de um médico")
    @GetMapping("/medico/{idMedico}/futuras")
    public ResponseEntity<List<ConsultaOutputDTO>> listarConsultasFuturasMedico(
            @Parameter(description = "ID do médico") @PathVariable Long idMedico) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarConsultasFuturasMedico(idMedico)));
    }

    @Operation(summary = "Listar consultas futuras de um paciente", description = "Retorna uma lista de consultas futuras de um paciente")
    @GetMapping("/paciente/{idPaciente}/futuras")
    public ResponseEntity<List<ConsultaOutputDTO>> listarConsultasFuturasPaciente(
            @Parameter(description = "ID do paciente") @PathVariable Long idPaciente) {
        return ResponseEntity.ok(mapToOutputDTOList(consultaService.listarConsultasFuturasPaciente(idPaciente)));
    }

    @PreAuthorize("hasAuthority('ROLE_MEDICO')")
    @PatchMapping("/{id}/realizar")
    public Consulta marcarComoRealizada(@PathVariable Long id) {
        return consultaService.marcarComoRealizada(id);
    }

    private List<ConsultaOutputDTO> mapToOutputDTOList(List<Consulta> consultas) {
        return consultas.stream()
                .map(consultaAssembler::toOutputDTO)
                .collect(Collectors.toList());
    }
}

package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.pacienteDTO.PacienteInputDTO;
import br.com.projeto.saude_plus.api.dto.pacienteDTO.PacienteOutputDTO;
import br.com.projeto.saude_plus.assembler.PacienteAssembler;
import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@Tag(name = "Paciente", description = "Endpoints relacionados ao paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteAssembler pacienteAssembler;

    @PostMapping
    public ResponseEntity<PacienteOutputDTO> cadastrar(@Valid @RequestBody PacienteInputDTO pacienteInputDTO) {
        Paciente paciente = pacienteAssembler.toEntity(pacienteInputDTO);
        Paciente novoPaciente = pacienteService.cadastrarPaciente(paciente);
        return ResponseEntity.ok(pacienteAssembler.toOutputDTO(novoPaciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteOutputDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteInputDTO pacienteInputDTO) {
        Paciente paciente = pacienteAssembler.toEntity(pacienteInputDTO);
        Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
        return ResponseEntity.ok(pacienteAssembler.toOutputDTO(pacienteAtualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteOutputDTO> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(pacienteAssembler.toOutputDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<List<PacienteOutputDTO>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<PacienteOutputDTO>> listarAtivos() {
        List<Paciente> pacientes = pacienteService.listarAtivos();
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<PacienteOutputDTO>> listarDesativados() {
        List<Paciente> pacientes = pacienteService.listarDesativados();
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        pacienteService.desativarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PacienteOutputDTO> buscarPorEmail(@PathVariable String email) {
        return pacienteService.buscarPorEmail(email)
                .map(pacienteAssembler::toOutputDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteOutputDTO> buscarPorCpf(@PathVariable String cpf) {
        return pacienteService.buscarPorCpf(cpf)
                .map(pacienteAssembler::toOutputDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PacienteOutputDTO>> buscarPorNome(@PathVariable String nome) {
        List<Paciente> pacientes = pacienteService.buscarPorNome(nome);
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    
    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/me")
    @Operation(summary = "Dados do paciente autenticado", description = "Retorna os dados do paciente atualmente autenticado via token JWT.")
    public ResponseEntity<PacienteOutputDTO> getPacienteLogado(HttpServletRequest request) {
        Paciente paciente = (Paciente) request.getUserPrincipal();
        return ResponseEntity.ok(pacienteAssembler.toOutputDTO(paciente));
    }

    private List<PacienteOutputDTO> mapToOutputDTOList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(pacienteAssembler::toOutputDTO)
                .collect(Collectors.toList());
    }
}

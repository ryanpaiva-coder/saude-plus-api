package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.pacienteDTO.PacienteInputDTO;
import br.com.projeto.saude_plus.api.dto.pacienteDTO.PacienteOutputDTO;
import br.com.projeto.saude_plus.assembler.PacienteAssembler;
import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Pacientes", description = "Operações relacionadas a pacientes")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteAssembler pacienteAssembler;

    @Operation(summary = "Cadastrar novo paciente", description = "Cria um novo paciente no sistema")
    @PostMapping
    public ResponseEntity<PacienteOutputDTO> cadastrar(
            @Valid @RequestBody PacienteInputDTO pacienteInputDTO) {
        Paciente paciente = pacienteAssembler.toEntity(pacienteInputDTO);
        Paciente novoPaciente = pacienteService.cadastrarPaciente(paciente);
        return ResponseEntity.ok(pacienteAssembler.toOutputDTO(novoPaciente));
    }

    @Operation(summary = "Atualizar paciente existente", description = "Atualiza os dados de um paciente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteOutputDTO> atualizar(
            @Parameter(description = "ID do paciente") @PathVariable Long id,
            @Valid @RequestBody PacienteInputDTO pacienteInputDTO) {
        Paciente paciente = pacienteAssembler.toEntity(pacienteInputDTO);
        Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
        return ResponseEntity.ok(pacienteAssembler.toOutputDTO(pacienteAtualizado));
    }

    @Operation(summary = "Buscar paciente por ID", description = "Retorna os dados de um paciente pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteOutputDTO> buscarPorId(
            @Parameter(description = "ID do paciente") @PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(pacienteAssembler.toOutputDTO(paciente));
    }

    @Operation(summary = "Listar todos os pacientes", description = "Retorna uma lista com todos os pacientes")
    @GetMapping
    public ResponseEntity<List<PacienteOutputDTO>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    @Operation(summary = "Listar pacientes ativos", description = "Retorna uma lista de pacientes ativos")
    @GetMapping("/ativos")
    public ResponseEntity<List<PacienteOutputDTO>> listarAtivos() {
        List<Paciente> pacientes = pacienteService.listarAtivos();
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    @Operation(summary = "Listar pacientes desativados", description = "Retorna uma lista de pacientes desativados")
    @GetMapping("/desativados")
    public ResponseEntity<List<PacienteOutputDTO>> listarDesativados() {
        List<Paciente> pacientes = pacienteService.listarDesativados();
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    @Operation(summary = "Desativar paciente por ID", description = "Desativa um paciente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(
            @Parameter(description = "ID do paciente") @PathVariable Long id) {
        pacienteService.desativarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar paciente por e-mail", description = "Retorna os dados de um paciente pelo e-mail")
    @GetMapping("/email/{email}")
    public ResponseEntity<PacienteOutputDTO> buscarPorEmail(
            @Parameter(description = "E-mail do paciente") @PathVariable String email) {
        return pacienteService.buscarPorEmail(email)
                .map(pacienteAssembler::toOutputDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar paciente por CPF", description = "Retorna os dados de um paciente pelo CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteOutputDTO> buscarPorCpf(
            @Parameter(description = "CPF do paciente") @PathVariable String cpf) {
        return pacienteService.buscarPorCpf(cpf)
                .map(pacienteAssembler::toOutputDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar pacientes por nome", description = "Retorna uma lista de pacientes pelo nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PacienteOutputDTO>> buscarPorNome(
            @Parameter(description = "Nome do paciente") @PathVariable String nome) {
        List<Paciente> pacientes = pacienteService.buscarPorNome(nome);
        return ResponseEntity.ok(mapToOutputDTOList(pacientes));
    }

    private List<PacienteOutputDTO> mapToOutputDTOList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(pacienteAssembler::toOutputDTO)
                .collect(Collectors.toList());
    }
}

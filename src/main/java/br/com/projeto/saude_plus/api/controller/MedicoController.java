package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoInputDTO;
import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoOutputDTO;
import br.com.projeto.saude_plus.assembler.MedicoAssembler;
import br.com.projeto.saude_plus.domain.model.Medico;
import br.com.projeto.saude_plus.domain.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Médicos", description = "Operações relacionadas a médicos")
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoAssembler medicoAssembler;

    @Operation(summary = "Cadastrar novo médico", description = "Cria um novo médico no sistema")
    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping
    public ResponseEntity<MedicoOutputDTO> cadastrar(
            @Valid @RequestBody MedicoInputDTO medicoInputDTO) {
        Medico medico = medicoAssembler.toEntity(medicoInputDTO);
        Medico novoMedico = medicoService.cadastrarMedico(
            medico,
            medicoInputDTO.getEspecialidade().getId()
        );
        return ResponseEntity.ok(medicoAssembler.toOutputDTO(novoMedico));
    }

    @Operation(summary = "Atualizar médico existente", description = "Atualiza os dados de um médico pelo ID")
    @PreAuthorize("hasRole('GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicoOutputDTO> atualizar(
            @Parameter(description = "ID do médico") @PathVariable Long id,
            @Valid @RequestBody MedicoInputDTO medicoInputDTO) {
        Medico medico = medicoAssembler.toEntity(medicoInputDTO);
        Medico medicoAtualizado = medicoService.atualizarMedico(id, medico);
        return ResponseEntity.ok(medicoAssembler.toOutputDTO(medicoAtualizado));
    }

    @Operation(summary = "Buscar médico por ID", description = "Retorna os dados de um médico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<MedicoOutputDTO> buscarPorId(
            @Parameter(description = "ID do médico") @PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medicoAssembler.toOutputDTO(medico));
    }

    @Operation(summary = "Listar todos os médicos", description = "Retorna uma lista com todos os médicos")
    @PreAuthorize("hasRole('GERENTE')")
    @GetMapping
    public ResponseEntity<List<MedicoOutputDTO>> listarTodos() {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.listarTodos()));
    }

    @Operation(summary = "Listar médicos ativos", description = "Retorna uma lista de médicos ativos")
    @GetMapping("/ativos")
    public ResponseEntity<List<MedicoOutputDTO>> listarAtivos() {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.listarAtivos()));
    }

    @Operation(summary = "Listar médicos desativados", description = "Retorna uma lista de médicos desativados")
    @PreAuthorize("hasRole('GERENTE')")
    @GetMapping("/desativados")
    public ResponseEntity<List<MedicoOutputDTO>> listarDesativados() {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.listarDesativados()));
    }

    @Operation(summary = "Desativar médico por ID", description = "Desativa um médico pelo ID")
    @PreAuthorize("hasRole('GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(
            @Parameter(description = "ID do médico") @PathVariable Long id) {
        medicoService.desativarMedico(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar médico por CRM", description = "Retorna os dados de um médico pelo CRM")
    @GetMapping("/crm/{crm}")
    public ResponseEntity<MedicoOutputDTO> buscarPorCrm(
            @Parameter(description = "CRM do médico") @PathVariable String crm) {
        return medicoService.buscarPorCrm(crm)
                .map(medicoAssembler::toOutputDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar médicos por especialidade", description = "Retorna uma lista de médicos pela especialidade")
    @GetMapping("/especialidade/{nomeEspecialidade}")
    public ResponseEntity<List<MedicoOutputDTO>> buscarPorEspecialidade(
            @Parameter(description = "Nome da especialidade") @PathVariable String nomeEspecialidade) {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.buscarPorEspecialidade(nomeEspecialidade)));
    }

    @Operation(summary = "Buscar médicos por nome", description = "Retorna uma lista de médicos pelo nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<MedicoOutputDTO>> buscarPorNome(
            @Parameter(description = "Nome do médico") @PathVariable String nome) {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.buscarPorNome(nome)));
    }

    private List<MedicoOutputDTO> mapToOutputDTOList(List<Medico> medicos) {
        return medicos.stream()
                .map(medicoAssembler::toOutputDTO)
                .collect(Collectors.toList());
    }
}

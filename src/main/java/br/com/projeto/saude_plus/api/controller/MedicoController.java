package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoInputDTO;
import br.com.projeto.saude_plus.api.dto.medicoDTO.MedicoOutputDTO;
import br.com.projeto.saude_plus.assembler.MedicoAssembler;
import br.com.projeto.saude_plus.domain.model.Medico;
import br.com.projeto.saude_plus.domain.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoAssembler medicoAssembler;

    @PostMapping
    public ResponseEntity<MedicoOutputDTO> cadastrar(@Valid @RequestBody MedicoInputDTO medicoInputDTO) {
        Medico medico = medicoAssembler.toEntity(medicoInputDTO);
        Medico novoMedico = medicoService.cadastrarMedico(medico);
        return ResponseEntity.ok(medicoAssembler.toOutputDTO(novoMedico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoOutputDTO> atualizar(@PathVariable Long id, @Valid @RequestBody MedicoInputDTO medicoInputDTO) {
        Medico medico = medicoAssembler.toEntity(medicoInputDTO);
        Medico medicoAtualizado = medicoService.atualizarMedico(id, medico);
        return ResponseEntity.ok(medicoAssembler.toOutputDTO(medicoAtualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoOutputDTO> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medicoAssembler.toOutputDTO(medico));
    }

    @GetMapping
    public ResponseEntity<List<MedicoOutputDTO>> listarTodos() {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.listarTodos()));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<MedicoOutputDTO>> listarAtivos() {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.listarAtivos()));
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<MedicoOutputDTO>> listarDesativados() {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.listarDesativados()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        medicoService.desativarMedico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<MedicoOutputDTO> buscarPorCrm(@PathVariable String crm) {
        return medicoService.buscarPorCrm(crm)
                .map(medicoAssembler::toOutputDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidade/{nomeEspecialidade}")
    public ResponseEntity<List<MedicoOutputDTO>> buscarPorEspecialidade(@PathVariable String nomeEspecialidade) {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.buscarPorEspecialidade(nomeEspecialidade)));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<MedicoOutputDTO>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(mapToOutputDTOList(medicoService.buscarPorNome(nome)));
    }

    private List<MedicoOutputDTO> mapToOutputDTOList(List<Medico> medicos) {
        return medicos.stream()
                .map(medicoAssembler::toOutputDTO)
                .collect(Collectors.toList());
    }
}

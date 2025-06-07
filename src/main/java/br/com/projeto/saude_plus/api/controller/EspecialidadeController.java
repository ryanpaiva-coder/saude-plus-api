package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.especialidadeDTO.EspecialidadeInputDTO;
import br.com.projeto.saude_plus.api.dto.especialidadeDTO.EspecialidadeOutputDTO;
import br.com.projeto.saude_plus.assembler.EspecialidadeAssembler;
import br.com.projeto.saude_plus.domain.model.Especialidade;
import br.com.projeto.saude_plus.domain.service.EspecialidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private EspecialidadeAssembler especialidadeAssembler;

    @GetMapping
    public ResponseEntity<List<EspecialidadeOutputDTO>> listarTodas() {
        List<Especialidade> especialidades = especialidadeService.listarTodas();
        List<EspecialidadeOutputDTO> especialidadeDTOs = especialidades.stream()
                .map(especialidadeAssembler::toOutputDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(especialidadeDTOs);
    }

    @PostMapping
    public ResponseEntity<EspecialidadeOutputDTO> cadastrar(
            @Valid @RequestBody EspecialidadeInputDTO especialidadeInputDTO) {
        Especialidade especialidade = especialidadeAssembler.toEntity(especialidadeInputDTO);
        Especialidade especialidadeSalva = especialidadeService.cadastrar(especialidade);
        EspecialidadeOutputDTO especialidadeDTO = especialidadeAssembler.toOutputDTO(especialidadeSalva);
        return ResponseEntity.ok(especialidadeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeOutputDTO> buscarPorId(@PathVariable Long id) {
        Especialidade especialidade = especialidadeService.buscarPorId(id);
        EspecialidadeOutputDTO especialidadeDTO = especialidadeAssembler.toOutputDTO(especialidade);
        return ResponseEntity.ok(especialidadeDTO);
    }
}

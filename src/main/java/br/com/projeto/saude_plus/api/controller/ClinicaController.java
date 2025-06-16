package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.clinicaDTO.ClinicaInputDTO;
import br.com.projeto.saude_plus.api.dto.clinicaDTO.ClinicaOutputDTO;
import br.com.projeto.saude_plus.assembler.ClinicaAssembler;
import br.com.projeto.saude_plus.domain.model.Clinica;
import br.com.projeto.saude_plus.domain.service.ClinicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clínica", description = "Operações relacionadas à clínica")
@RestController
@RequestMapping("/api/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private ClinicaAssembler clinicaAssembler;

    @Operation(summary = "Buscar clínica por ID", description = "Retorna os dados da clínica pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClinicaOutputDTO> buscarClinica(
            @Parameter(description = "ID da clínica") @PathVariable Long id) {
        Clinica clinica = clinicaService.buscarPorId(id);
        return ResponseEntity.ok(clinicaAssembler.toOutputDTO(clinica));
    }

    @Operation(summary = "Atualizar clínica", description = "Atualiza os dados da clínica pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<ClinicaOutputDTO> atualizarClinica(
            @Parameter(description = "ID da clínica") @PathVariable Long id,
            @Valid @RequestBody ClinicaInputDTO clinicaInputDTO) {
        Clinica clinicaAtualizada = clinicaService.atualizar(
                id,
                clinicaAssembler.toEntity(clinicaInputDTO)
        );
        return ResponseEntity.ok(clinicaAssembler.toOutputDTO(clinicaAtualizada));
    }
}

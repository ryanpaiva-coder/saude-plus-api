package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.clinicaDTO.ClinicaInputDTO;
import br.com.projeto.saude_plus.api.dto.clinicaDTO.ClinicaOutputDTO;
import br.com.projeto.saude_plus.assembler.ClinicaAssembler;
import br.com.projeto.saude_plus.domain.model.Clinica;
import br.com.projeto.saude_plus.domain.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private ClinicaAssembler clinicaAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaOutputDTO> buscarClinica(@PathVariable Long id) {
        Clinica clinica = clinicaService.buscarClinica(id);
        return ResponseEntity.ok(clinicaAssembler.toOutputDTO(clinica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaOutputDTO> atualizarClinica(
            @PathVariable Long id,
            @Valid @RequestBody ClinicaInputDTO clinicaInputDTO) {
        Clinica clinicaAtualizada = clinicaService.atualizarClinica(
                id,
                clinicaAssembler.toEntity(clinicaInputDTO)
        );
        return ResponseEntity.ok(clinicaAssembler.toOutputDTO(clinicaAtualizada));
    }
}

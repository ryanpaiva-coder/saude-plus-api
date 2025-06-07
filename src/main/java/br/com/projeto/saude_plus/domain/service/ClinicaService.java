package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Clinica;
import br.com.projeto.saude_plus.domain.repository.ClinicaRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    public Clinica buscarPorId(Long id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
    }

    @Transactional
    public Clinica atualizar(Long id, Clinica dadosAtualizados) {
        Clinica clinica = buscarPorId(id);
        clinica.setNomeFantasia(dadosAtualizados.getNomeFantasia());
        clinica.setTelefone(dadosAtualizados.getTelefone());
        clinica.setEndereco(dadosAtualizados.getEndereco());
        return clinicaRepository.save(clinica);
    }
}
package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteUserDetailsService implements UserDetailsService {

    private final PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return pacienteRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Paciente não encontrado com o email: " + email));
    }
}

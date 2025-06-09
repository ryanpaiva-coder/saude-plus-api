package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.api.dto.authDTO.AuthRequest;
import br.com.projeto.saude_plus.api.dto.authDTO.AuthResponse;
import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.model.Token;
import br.com.projeto.saude_plus.domain.repository.PacienteRepository;
import br.com.projeto.saude_plus.domain.repository.TokenRepository;
import br.com.projeto.saude_plus.infra.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PacienteRepository pacienteRepository;
    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        Paciente paciente = pacienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        String jwtToken = jwtUtil.generateToken(paciente.getId(), paciente.getEmail());

        Token token = Token.builder()
                .token(jwtToken)
                .usuario(paciente)
                .expirado(false)
                .revogado(false)
                .build();

        tokenRepository.save(token);

        return new AuthResponse(jwtToken);
    }

    @Transactional
    public void logout(String tokenJwt) {
        Token token = tokenRepository.findByToken(tokenJwt)
            .orElseThrow(() -> new RuntimeException("Token não encontrado"));

        token.setExpirado(true);
        token.setRevogado(true);
        tokenRepository.save(token);
    }
}

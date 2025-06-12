package br.com.projeto.saude_plus.infra.security;

import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.model.Token;
import br.com.projeto.saude_plus.domain.repository.PacienteRepository;
import br.com.projeto.saude_plus.domain.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final PacienteRepository pacienteRepository;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String email;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        email = jwtUtil.getEmailFromToken(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Token> tokenOptional = tokenRepository.findByToken(token);

            if (tokenOptional.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            Token tokenObj = tokenOptional.get();
            boolean tokenValido = !tokenObj.isExpirado() && !tokenObj.isRevogado();

            Paciente paciente = pacienteRepository.findByEmail(email).orElse(null);
            if (paciente != null && jwtUtil.validateToken(token) && tokenValido) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        paciente, null, paciente.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

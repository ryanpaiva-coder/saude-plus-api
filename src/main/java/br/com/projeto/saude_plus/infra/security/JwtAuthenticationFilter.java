package br.com.projeto.saude_plus.infra.security;

import br.com.projeto.saude_plus.domain.model.Usuario;
import br.com.projeto.saude_plus.domain.repository.TokenRepository;
import br.com.projeto.saude_plus.domain.repository.UsuarioRepository;
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

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        if (isPublicEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtUtil.getEmailFromToken(token);

        if (shouldAuthenticate(email)) {
            authenticateUser(token, email, request);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();
        return (path.equals("/api/pacientes") && method.equals("POST")) || path.startsWith("/api/auth");
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    private boolean shouldAuthenticate(String email) {
        return email != null && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void authenticateUser(String token, String email, HttpServletRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        boolean tokenValido = tokenRepository.findByToken(token)
                .filter(t -> !t.isExpirado() && !t.isRevogado())
                .isPresent();

        if (usuario != null && jwtUtil.validateToken(token) && tokenValido) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}

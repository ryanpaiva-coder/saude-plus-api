package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.authDTO.AuthRequest;
import br.com.projeto.saude_plus.api.dto.authDTO.AuthResponse;
import br.com.projeto.saude_plus.domain.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "login", description = "Endpoints de login e autenticação com JWT")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login com email e senha", description = "Autentica o paciente e retorna um token JWT válido.")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

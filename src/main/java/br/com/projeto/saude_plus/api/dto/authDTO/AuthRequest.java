package br.com.projeto.saude_plus.api.dto.authDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String email;
    private String senha;
}

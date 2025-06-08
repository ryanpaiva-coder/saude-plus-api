package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.model.Token;
import br.com.projeto.saude_plus.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT t FROM Token t WHERE t.usuario = :usuario AND (t.expirado = false OR t.revogado = false)")
    List<Token> findAllValidTokensByUsuario(Usuario usuario);

    Optional<Token> findByToken(String token);
}

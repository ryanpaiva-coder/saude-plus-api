package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}

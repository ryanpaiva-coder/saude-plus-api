package br.com.projeto.saude_plus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.saude_plus.domain.model.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {

}

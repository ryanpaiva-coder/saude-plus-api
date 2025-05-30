package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNome(String nome);
    
}
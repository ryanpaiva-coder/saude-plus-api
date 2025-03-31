package br.com.projeto.saude_plus.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.saude_plus.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}

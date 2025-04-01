package br.com.projeto.saude_plus.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.saude_plus.domain.model.User;
import br.com.projeto.saude_plus.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User register(final User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(final User user, UUID userId) {

        User findUser = search(userId);
        findUser.setName(user.getName());

        return userRepository.save(findUser);
    }

    @Transactional
    public User search(final UUID userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public List<User> list() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}

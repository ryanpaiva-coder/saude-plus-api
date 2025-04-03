package br.com.projeto.saude_plus.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.saude_plus.domain.model.User;
import br.com.projeto.saude_plus.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.list();
        return ResponseEntity.ok(users);
    }
}

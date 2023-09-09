package com.codingdock.restfulapi.controllers;

import com.codingdock.restfulapi.models.User;
import com.codingdock.restfulapi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping()
    public User add(@RequestParam String name, @RequestParam String email) {
        var user = new User(name, email);
        return userRepository.save(user);
    }

    @GetMapping()
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        var user = userRepository.findById(id);
        return user.orElse(null);
    }

    @PutMapping("/{id}")

    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @PatchMapping("/{id}")

    public User partialUpdate(@PathVariable("id") Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAll() {
        userRepository.deleteAll();
    }

}
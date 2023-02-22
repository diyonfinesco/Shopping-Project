package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) {
        User registeredUser = this.userRepository.findByUsername(user.getUsername());

        if (registeredUser != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already in use!");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public Map<String, String> loginUser(String username, String password) {
        User registeredUser = this.userRepository.findByUsername(username);

        if (registeredUser == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username does not exist!");

        if (!bCryptPasswordEncoder.matches(password, registeredUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password incorrect!");
        }

        Map<String, String> tokens = new HashMap<String, String>();
        tokens.put("username", "customer");
        tokens.put("password", "customer");

        return tokens;
    }

    @Override
    public List<User> readAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    }

    @Override
    public User updateUser(String id, User User) {
        Optional<User> userDB = this.userRepository.findById(id);

        if (userDB.isPresent()) {
            User updatedUser = userDB.get();

            updatedUser.setName(User.getName());
            updatedUser.setUsername(User.getUsername());
            this.userRepository.save(updatedUser);

            return updatedUser;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    }

    @Override
    public void deleteUser(String id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            this.userRepository.delete(user.get());
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    }
}

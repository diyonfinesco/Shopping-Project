package com.example.demo.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        User registeredUser = this.userRepository.findByUsername(user.getUsername());

        if (registeredUser != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already in use!");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public Map<String, Object> loginUser(String username, String password) {
        User registeredUser = this.userRepository.findByUsername(username);

        if (registeredUser == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username does not exist!");

        if (!passwordEncoder.matches(password, registeredUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password incorrect!");
        }

        String h = username + ":" + password;
        String token = Base64.getEncoder().encodeToString(h.getBytes());

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", registeredUser);
        data.put("token", token);
        return data;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}

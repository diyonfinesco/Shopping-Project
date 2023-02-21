package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.UserDTO;
import com.example.demo.models.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController()
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    // create
    @PostMapping(value = "/users")
    public User create(@Valid @RequestBody UserDTO userDTO) {
        User userRequest = modelMapper.map(userDTO, User.class);
        return this.userService.createUser(userRequest);
    }

    // real all
    @GetMapping(value = "/users")
    public List<User> getAllUser() {
        return this.userService.readAllUsers();
    }

    // read one
    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable String id) {
        return this.userService.getUserById(id);
    }

    // update
    @PutMapping(value = "/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User userRequest = modelMapper.map(userDTO, User.class);
        return this.userService.updateUser(id, userRequest);
    }

    // delete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
    }
}

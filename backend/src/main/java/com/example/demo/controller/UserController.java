package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.UpdateUserDTO;
import com.example.demo.dto.user.CreateUserDTO;
import com.example.demo.dto.user.LoginUserDTO;
import com.example.demo.models.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // register
    @PostMapping(value = "/register")
    public User create(@Valid @RequestBody CreateUserDTO requestUserDTO) {
        return this.userService.createUser(modelMapper.map(requestUserDTO, User.class));
    }

    // login
    @PostMapping(value = "/login")
    public Map<String, String> loginUser(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        return this.userService.loginUser(loginUserDTO.getUsername(), loginUserDTO.getPassword());
    }

    // real all
    @GetMapping()
    public List<User> getAllUser() {
        return this.userService.readAllUsers();
    }

    // read one
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable String id) {
        return this.userService.getUserById(id);
    }

    // update
    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UpdateUserDTO updateUserDTO) {
        return this.userService.updateUser(id, modelMapper.map(updateUserDTO, User.class));
    }

    // delete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
    }
}

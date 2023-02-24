package com.example.demo.controller;

import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Void> create(@Valid @RequestBody CreateUserDTO requestUserDTO) {
        userService.createUser(modelMapper.map(requestUserDTO, User.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // login
    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        var token = this.userService.loginUser(loginUserDTO.getUsername(), loginUserDTO.getPassword());
        return ResponseEntity.ok(token);
    }
}

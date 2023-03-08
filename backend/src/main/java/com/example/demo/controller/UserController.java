package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.user.CreateUserDTO;
import com.example.demo.dto.user.LoginUserDTO;
import com.example.demo.models.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // register
    @PostMapping(value = "/register")
    public ResponseEntity<Void> create(@Valid @RequestBody CreateUserDTO creteUserDTO) {
        userService.createUser(modelMapper.map(creteUserDTO, User.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // login
    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        var token = this.userService.loginUser(loginUserDTO.getUsername(), loginUserDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    // read all users
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(this.userService.getUsers());
    }
}

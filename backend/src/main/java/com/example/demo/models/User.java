package com.example.demo.models;

import com.example.demo.security.ApplicationUserRole;
import com.example.demo.security.ApplicationUserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private ApplicationUserRole role = ApplicationUserRole.CUSTOMER;

    @NotNull
    @Size(max = 30)
    @Indexed(unique = true)
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    public User() {
    }

    public User( String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationUserRole getRole() {
        return role;
    }

    public void setRole(ApplicationUserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

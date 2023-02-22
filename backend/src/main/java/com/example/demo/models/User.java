package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

enum ROLE {
    CUSTOMER,
    ADMIN
}

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private ROLE role = ROLE.CUSTOMER;

    @NotNull
    @Size(max = 30)
    @Indexed(unique = true)
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    public User() {

    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ROLE getRole() {
        return this.role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

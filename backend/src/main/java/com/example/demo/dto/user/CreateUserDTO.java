package com.example.demo.dto.user;

import jakarta.validation.constraints.NotNull;

public class CreateUserDTO {
    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

package com.example.demo.dto.user;

import jakarta.validation.constraints.NotNull;

public class LoginUserDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;

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

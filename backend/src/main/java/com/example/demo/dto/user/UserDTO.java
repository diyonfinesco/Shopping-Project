package com.example.demo.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private String id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 30)
    private String email;

    @NotNull
    @Size(max = 30)
    private String password;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

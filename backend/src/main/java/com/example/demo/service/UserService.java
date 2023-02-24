package com.example.demo.service;

import com.example.demo.models.User;
import java.util.Map;

public interface UserService {
    public User createUser(User user);

    public Map<String, String> loginUser(String username, String password);
}

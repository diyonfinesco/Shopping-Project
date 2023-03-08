package com.example.demo.service;

import com.example.demo.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User createUser(User user);

    public Map<String, Object> loginUser(String username, String password);

    public List<User> getUsers();
}

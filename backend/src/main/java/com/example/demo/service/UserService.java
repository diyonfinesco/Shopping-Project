package com.example.demo.service;

import java.util.List;
import com.example.demo.models.User;
import java.util.Map;

public interface UserService {
    public User createUser(User user);

    public Map<String, String> loginUser(String username, String password);

    public List<User> readAllUsers();

    public User getUserById(String id);

    public User updateUser(String id, User user);

    public void deleteUser(String id);
}

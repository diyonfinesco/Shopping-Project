package com.example.demo.service;

import java.util.List;
import com.example.demo.models.User;

public interface UserService {
    public User createUser(User user);

    public List<User> readAllUsers();

    public User getUserById(String id);

    public User updateUser(String id, User user);

    public void deleteUser(String id);
}

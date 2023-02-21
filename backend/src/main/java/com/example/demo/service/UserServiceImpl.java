package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public User createUser(User user) {
        User registeredUser = this.UserRepository.findByEmail(user.getEmail());

        if (registeredUser != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use!");

        return this.UserRepository.save(user);
    }

    @Override
    public List<User> readAllUsers() {
        return this.UserRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        Optional<User> User = this.UserRepository.findById(id);

        if (User.isPresent()) {
            return User.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    }

    @Override
    public User updateUser(String id, User User) {
        Optional<User> UserDB = this.UserRepository.findById(id);

        if (UserDB.isPresent()) {
            User updatedUser = UserDB.get();

            updatedUser.setName(User.getName());
            updatedUser.setEmail(User.getEmail());
            updatedUser.setPassword(User.getPassword());
            this.UserRepository.save(updatedUser);

            return updatedUser;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    }

    @Override
    public void deleteUser(String id) {
        Optional<User> User = this.UserRepository.findById(id);

        if (User.isPresent()) {
            this.UserRepository.delete(User.get());
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    }
}

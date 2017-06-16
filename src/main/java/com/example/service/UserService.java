package com.example.service;

import com.example.model.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(Integer id);
    User getUserByName(String name);
}

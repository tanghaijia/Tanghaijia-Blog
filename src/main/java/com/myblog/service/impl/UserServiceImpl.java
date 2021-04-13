package com.myblog.service.impl;

import com.myblog.dao.UserRepository;
import com.myblog.field.User;
import com.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(value = "user-key")
    public User userCheck(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public List<User> users() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(i -> {users.add(i);});
        return users;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

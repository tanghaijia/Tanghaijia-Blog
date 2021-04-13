package com.myblog.service;

import com.myblog.field.User;

import java.util.List;

public interface UserService {
    User userCheck(String username, String password);

    List<User> users();

    /**通过username查找用户信息;*/
    public User findByUsername(String username);
}

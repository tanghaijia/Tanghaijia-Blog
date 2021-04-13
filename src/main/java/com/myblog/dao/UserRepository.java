package com.myblog.dao;

import com.myblog.field.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    /**通过username查找用户信息;*/
    public User findByUsername(String username);
}

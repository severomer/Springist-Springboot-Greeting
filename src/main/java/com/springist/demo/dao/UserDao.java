package com.springist.demo.dao;

import com.springist.demo.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public User findByUserId(Long userId);
    
    public void save(User user);
    
}

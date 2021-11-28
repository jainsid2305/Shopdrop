package com.example.shopdrop.services;

import com.example.shopdrop.models.User;

public interface UserService {

    public void save(User user);
    public User findByUsername(String email);
    public boolean userExists(String email);
    public User findByUserId(int id);


}


package com.corey.vidlib.dao;

import com.corey.vidlib.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao {

    User findByUserName(String userName);

    User findByEmail(String theEmail);
    
    User save(User user);

    User findById(Long id);
}

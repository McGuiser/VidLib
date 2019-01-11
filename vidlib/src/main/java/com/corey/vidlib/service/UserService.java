package com.corey.vidlib.service;

import com.corey.vidlib.entity.User;
import com.corey.vidlib.user.Channel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(Channel channel);

    UserDetails loadUserById(Long id);
}

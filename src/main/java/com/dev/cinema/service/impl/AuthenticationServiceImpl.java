package com.dev.cinema.service.impl;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user == null
                || !user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()))) {
            throw new AuthenticationException("Wrong login or password");
        }
        return user;
    }

    @Override
    public User register(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}

package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private static UserDao userDao;
    @Inject
    private static UserService userService;


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
    public User register(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user != null) {
            throw new AuthenticationException ("Not valid e-mail address: " + email);
        }
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}

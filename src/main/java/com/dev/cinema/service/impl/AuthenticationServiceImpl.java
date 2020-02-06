package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.exception.HashGeneratingException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private static UserDao userDao;

    @Override
    public User login(String email, String password) throws AuthenticationException {

        Optional<User> user = userDao.findByEmail(email);
        try {
            if (user.isEmpty() || !checkPassword(user.get(), password)) {
                throw new AuthenticationException("Wrong login or password");
            }
            return user.get();
        } catch (HashGeneratingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(User user, String password) throws HashGeneratingException {
        return user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()));
    }

    @Override
    public User register(String email, String password) {
        if (userDao.findByEmail(email).isPresent()) {
            throw new RuntimeException("Not valid e-mail address!");
        }
        User user = new User();
        user.setEmail(email);
        user.setSalt(HashUtil.getSalt());
        try {
            user.setPassword(HashUtil.hashPassword(password, user.getSalt()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userDao.add(user);
    }
}

package com.dev.cinema.service;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String name, String email, String password);
}

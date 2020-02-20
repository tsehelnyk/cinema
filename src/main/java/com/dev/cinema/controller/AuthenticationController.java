package com.dev.cinema.controller;

import com.dev.cinema.dto.UserRequestDto;
import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserRequestDto userDto) throws AuthenticationException {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        authenticationService.login(userDto.getEmail(), userDto.getPassword());
        return "user logined";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody UserRequestDto userDto) {
        authenticationService.register(userDto.getName(), userDto.getEmail(),
                userDto.getPassword());
        return "user registered";
    }
}

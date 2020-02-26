package com.dev.cinema.controller;

import com.dev.cinema.dto.UserDto;
import com.dev.cinema.dto.UserRegistrationDto;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(@RequestBody @Valid UserRegistrationDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userService.add(user);
        return "user added";
    }

    @RequestMapping(value = "/by-email", method = RequestMethod.GET)
    public UserDto getUser(@RequestParam("email") @Valid String email) {
        User user = userService.findByEmail(email);
        return new UserDto(user.getEmail(), user.getName(),
                user.getRoles().stream()
                        .map(role -> role.getRoleName()).collect(Collectors.toSet()));
    }
}

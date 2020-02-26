package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role("ADMIN");
        roleAdmin = roleService.add(roleAdmin);
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.addRole(roleAdmin);
        userService.add(admin);

        Role roleUser = new Role("USER");
        roleUser = roleService.add(roleUser);
        User user = new User();
        user.setEmail("joe@gmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        user.addRole(roleUser);
        userService.add(user);
    }
}
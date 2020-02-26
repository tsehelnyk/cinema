package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role("ADMIN");
        roleAdmin = roleService.add(roleAdmin);
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("123");
        admin.addRole(roleAdmin);
        userService.add(admin);

        Role roleUser = new Role("USER");
        roleUser = roleService.add(roleUser);
        User user = new User();
        user.setEmail("joe@gmail.com");
        user.setPassword("123");
        user.addRole(roleUser);
        userService.add(user);
    }
}

package com.dev.cinema.dto;

import com.dev.cinema.security.EmailConstraint;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    @NotNull(message = "User email could not be null")
    @Size(min = 5)
    @EmailConstraint
    private String email;
    @NotNull(message = "User name could not be null")
    private String name;
    @NotNull(message = "User roles could not be null")
    private Set<String> roles;

    public UserDto() {
    }

    public UserDto(String email, String name, Set<String> roles) {
        this.email = email;
        this.name = name;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

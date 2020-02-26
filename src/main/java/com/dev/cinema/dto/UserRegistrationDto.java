package com.dev.cinema.dto;

import com.dev.cinema.security.EmailConstraint;
import com.dev.cinema.security.FieldsValueMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Passwords do not match!"
        )
})
public class UserRegistrationDto {
    @NotNull(message = "User name could not be null")
    private String name;
    @NotNull(message = "User email could not be null")
    @Size(min = 5)
    @EmailConstraint
    private String email;
    @NotNull(message = "User password could not be null")
    @Size(min = 3)
    private String password;
    @NotNull(message = "User repeated password could not be null")
    @Size(min = 3)
    private String repeatPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

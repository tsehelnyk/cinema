package com.dev.cinema.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void initialize(EmailConstraint email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        matcher = pattern.matcher(contactField);
        return contactField != null && matcher.matches();
    }
}

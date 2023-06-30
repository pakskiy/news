package com.pakskiy.news.validator;

import com.pakskiy.news.exception.WrongFormatException;
import org.apache.commons.validator.routines.EmailValidator;

public class ValidateEmail {
    private final String mEmail;

    public ValidateEmail(String email) throws Error {
        if (!isValidEmail(email)) {
            throw new WrongFormatException("Invalid email format");
        }
        this.mEmail = email;
    }

    private static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public String value() {
        return mEmail;
    }

}

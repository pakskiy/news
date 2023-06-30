package com.pakskiy.news.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.pakskiy.news.exception.WrongFormatException;

public class ValidatePhone {
    private final String mPhone;

    public ValidatePhone(String phone) throws Error {
        if (!isValidPhoneNumber(phone)) {
            throw new WrongFormatException("Invalid phone format");
        }
        this.mPhone = phone;
    }

    public static boolean isValidPhoneNumber(String phone) {
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phone, "");

            if (!PhoneNumberUtil.getInstance().isPossibleNumber(phoneNumber)) {
                return false;
            }
        } catch (NumberParseException e) {
            return false;
        }
        return true;
    }

    public String value() {
        return mPhone;
    }
}
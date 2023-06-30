package com.pakskiy.news.exception;

public class WrongFormatException extends RuntimeException {
    public WrongFormatException(String message) {
        super(message);
    }
    public WrongFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

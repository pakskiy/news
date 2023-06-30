package com.pakskiy.news.exception;

public class OutOfLimitException extends RuntimeException {
    public OutOfLimitException(String message) {
        super(message);
    }

    public OutOfLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}

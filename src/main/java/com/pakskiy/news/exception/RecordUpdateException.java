package com.pakskiy.news.exception;

public class RecordUpdateException extends RuntimeException {
    public RecordUpdateException(String message) {
        super(message);
    }
    public RecordUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}

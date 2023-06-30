package com.pakskiy.news.exception;

public class RecordDeleteException extends RuntimeException {
    public RecordDeleteException(String message) {
        super(message);
    }
    public RecordDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}

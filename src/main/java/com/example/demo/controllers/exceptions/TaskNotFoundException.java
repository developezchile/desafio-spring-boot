package com.example.demo.controllers.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TaskNotFoundException(final String message) {
        super(message);
    }

    public TaskNotFoundException(final Throwable cause) {
        super(cause);
    }

}

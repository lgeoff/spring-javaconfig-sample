package com.example.springtemplate.exceptions;

import org.springframework.validation.Errors;

/**
 * Created by lgeoff on 03.07.2015.
 */
public class InvalidRestBookException extends RuntimeException {
    private Errors errors;

    public InvalidRestBookException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() { return errors; }
}

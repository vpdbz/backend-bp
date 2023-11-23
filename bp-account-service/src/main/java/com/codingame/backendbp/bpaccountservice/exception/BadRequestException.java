package com.codingame.backendbp.bpaccountservice.exception;

import org.springframework.validation.Errors;

public class BadRequestException extends RuntimeException {
    private final transient Errors errors;

    public BadRequestException(Errors errors) {
        super("Bad request");
        this.errors = errors;
    }

    public Errors getErrors() {
        return this.errors;
    }
}

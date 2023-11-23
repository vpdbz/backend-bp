package com.codingame.backendbp.bpaccountservice.exception;

public class NoFundsException extends RuntimeException {
    public NoFundsException(String message) {
        super(message);
    }

    public NoFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

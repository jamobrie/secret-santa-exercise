package com.secretsanta.exception;

public class InvalidTeammateException extends RuntimeException {
    public InvalidTeammateException(String exceptionMessage) {
        super(exceptionMessage);
    }
}

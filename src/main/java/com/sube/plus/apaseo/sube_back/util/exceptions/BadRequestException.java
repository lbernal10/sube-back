package com.sube.plus.apaseo.sube_back.util.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
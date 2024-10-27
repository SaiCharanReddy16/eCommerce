package com.ecommerce.project.exceptions;

public class NoElementException extends RuntimeException {

    public NoElementException() {}

    public NoElementException(String message) {
        super(message);
    }
}

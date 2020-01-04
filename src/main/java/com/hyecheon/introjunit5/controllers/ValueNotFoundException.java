package com.hyecheon.introjunit5.controllers;

public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException() {
    }

    public ValueNotFoundException(String message) {
        super(message);
    }
}

package com.hyecheon.introjunit5.fauxspring;

public interface BindingResult {
    void rejectValue(String lastName, String notFound, String not_found);

    boolean hasErrors();
}
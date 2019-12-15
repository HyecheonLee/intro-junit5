package com.hyecheon.introjunit5.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void groupedAssertions() {
        //given
        final Person person = new Person(1L, "Joe", "Buck");
        //when
        assertAll("Test Props Set",
                () -> assertEquals(person.getFirstName(), "Joe"),
                () -> assertEquals(person.getLastName(), "Buck"));
    }

    @Test
    void groupedAssertionMsgs() {
        //given
        final Person person = new Person(1L, "Joe", "Buck");

        //when
        assertAll("Test Props Set",
                () -> assertEquals(person.getFirstName(), "Joe2", "First Name Failed"),
                () -> assertEquals(person.getLastName(), "Buckxx", "Last Name Failed"));

    }
}
package com.hyecheon.introjunit5.model;

import com.hyecheon.introjunit5.ModelTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {
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
    void groupedAssertionMsg() {
        //given
        final Person person = new Person(1L, "Joe", "Buck");

        //when
        assertAll("Test Props Set",
                () -> assertEquals(person.getFirstName(), "Joe", "First Name Failed"),
                () -> assertEquals(person.getLastName(), "Buck", "Last Name Failed"));

    }
}
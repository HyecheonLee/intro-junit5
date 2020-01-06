package com.hyecheon.introjunit5.model;

import com.hyecheon.introjunit5.ModelTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {
    @Test
    void dependentAssertions() {
        final Owner owner = new Owner(1l, "joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
                () -> assertAll(
                        "Person Properties"
                        , () -> assertEquals("joe", owner.getFirstName(), "First Name did not Match")
                        , () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity(), "City Did Not Match"),
                        () -> assertEquals("1231231234", owner.getTelephone())));
        assertThat(owner.getCity(), is("Key West"));
    }
}
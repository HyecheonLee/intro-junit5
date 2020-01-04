package com.hyecheon.introjunit5.service.springdatajpa;

import com.hyecheon.introjunit5.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled(value = "disable 우리가 mock 을 배우기 전까지!")
class OwnerSDJpaServiceTest {
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
        service = new OwnerSDJpaService(null, null, null);
    }


    @Disabled
    @Test
    void findByLastName() {
        final var foundOwner = service.findByLastName("Buck");

    }

    @Test
    void findAllByLastNameLike() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }
}
package com.hyecheon.introjunit5.service.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import com.hyecheon.introjunit5.model.Speciality;
import com.hyecheon.introjunit5.repositories.SpecialtyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

  @Mock
  SpecialtyRepository specialtyRepository;

  @InjectMocks
  SpecialitySDJpaService service;

  @Test
  void delete() {
    service.deleteById(1L);
  }

  @Test
  void testDelete() {
    service.delete(new Speciality());
  }
}
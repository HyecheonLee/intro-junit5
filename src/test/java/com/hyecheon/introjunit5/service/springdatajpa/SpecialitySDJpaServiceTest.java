package com.hyecheon.introjunit5.service.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
  void deleteById() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, times(2)).deleteById(1L);
  }

  @Test
  void deleteByIdAtLest() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, atLeastOnce()).deleteById(1L);
  }

  @Test
  void deleteByIdAtMost() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, atMost(5)).deleteById(1L);
  }

  @Test
  void deleteByIdAtNever() {
    service.deleteById(1L);
    service.deleteById(1L);

    verify(specialtyRepository, atLeastOnce()).deleteById(1L);

    verify(specialtyRepository, never()).deleteById(5L);
  }

  @Test
  void testDelete() {
    service.delete(new Speciality());
    service.delete(new Speciality());


  }
}
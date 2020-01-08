package com.hyecheon.introjunit5.service.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.hyecheon.introjunit5.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

  @Mock
  VetRepository vetRepository;

  @InjectMocks
  VetSDJpaService service;

  @Test
  void deleteById() {
    service.deleteById(1L);

    verify(vetRepository).deleteById(1L);
  }
}
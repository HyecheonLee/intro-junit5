package com.hyecheon.introjunit5.service.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hyecheon.introjunit5.model.Speciality;
import com.hyecheon.introjunit5.repositories.SpecialtyRepository;
import java.util.Optional;
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
  void testDeleteByObject() {
    //given
    final var speciality = new Speciality();

    //when
    service.delete(speciality);

    //then
    then(specialtyRepository).should().delete(any(Speciality.class));
  }


  @Test
  void findByIdTest() {
    final var speciality = new Speciality();

    when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

    final var foundSpecialty = service.findById(1L);

    assertThat(foundSpecialty).isNotNull();

    verify(specialtyRepository).findById(anyLong());
  }

  @Test
  void findByIdBddTest() {
    //given
    final var speciality = new Speciality();
    given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

    //when
    final var foundSpeciality = service.findById(1L);

    //then
    assertThat(foundSpeciality).isNotNull();
    then(specialtyRepository).should(times(1)).findById(anyLong());
    then(specialtyRepository).shouldHaveNoMoreInteractions();
  }

  @Test
  void deleteById() {
    //given - none

    //when
    service.deleteById(1L);
    service.deleteById(1L);

    //then
    then(specialtyRepository).should(times(2)).deleteById(1L);
  }

  @Test
  void deleteByIdAtLest() {
    //given

    //when
    service.deleteById(1L);
    service.deleteById(1L);
    //then
    then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
  }

  @Test
  void deleteByIdAtMost() {
    //given

    //when
    service.deleteById(1L);
    service.deleteById(1L);

    //then
    then(specialtyRepository).should(atMost(5)).deleteById(1L);
  }

  @Test
  void deleteByIdAtNever() {
    //when
    service.deleteById(1L);
    service.deleteById(1L);

    //then
    then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
    then(specialtyRepository).should(never()).deleteById(5L);
  }

  @Test
  void testDelete() {
    //when
    service.delete(new Speciality());

    //then
    then(specialtyRepository).should().delete(any());
  }
}
package com.hyecheon.introjunit5.service.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hyecheon.introjunit5.model.Speciality;
import com.hyecheon.introjunit5.repositories.SpecialtyRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

  @Mock(lenient = true)
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
    then(specialtyRepository).should(timeout(100).times(2)).deleteById(1L);
  }

  @Test
  void deleteByIdAtLest() {
    //given

    //when
    service.deleteById(1L);
    service.deleteById(1L);
    //then
    then(specialtyRepository).should(timeout(1000).atLeastOnce()).deleteById(1L);
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
    then(specialtyRepository).should(timeout(200).atLeastOnce()).deleteById(1L);
    then(specialtyRepository).should(never()).deleteById(5L);
  }

  @Test
  void testDelete() {
    //when
    service.delete(new Speciality());

    //then
    then(specialtyRepository).should().delete(any());
  }

  @Test
  void testDoThrow() {
    doThrow(new RuntimeException()).when(specialtyRepository).delete(any());

    assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

    verify(specialtyRepository).delete(any());
  }

  @Test
  void testFindByIDThrow() {
    given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

    assertThrows(RuntimeException.class, () -> service.findById(1L));

    then(specialtyRepository).should().findById(1L);
  }

  @Test
  void testDeleteBDD() {
    willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());

    assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

    then(specialtyRepository).should().delete(any());
  }

  @Test
  void testSaveLambda() {
    final String MATCH_ME = "MATCH_ME";
    final var speciality = new Speciality();
    speciality.setDescription(MATCH_ME);

    final var savedSpeciality = new Speciality();
    savedSpeciality.setId(1L);

    //given
    given(
        specialtyRepository.save(
            argThat(argument -> argument.getDescription().equals(MATCH_ME))))
        .willReturn(savedSpeciality);

    //when
    final var returnedSpecialty = service.save(speciality);

    //then
    assertThat(returnedSpecialty.getId()).isEqualTo(1L);
  }

  @Test
  void testSaveLambdaNoMatch() {
    final String MATCH_ME = "MATCH_ME";
    final var speciality = new Speciality();
    speciality.setDescription("Not a match");

    final var savedSpeciality = new Speciality();
    savedSpeciality.setId(1L);

    //given
    given(
        specialtyRepository.save(
            argThat(argument -> argument.getDescription().equals(MATCH_ME))))
        .willReturn(savedSpeciality);

    //when
    final var returnedSpecialty = service.save(speciality);

    //then
    assertNull(returnedSpecialty);
  }
}
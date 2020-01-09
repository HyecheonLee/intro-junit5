package com.hyecheon.introjunit5.service.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hyecheon.introjunit5.model.Visit;
import com.hyecheon.introjunit5.repositories.VisitRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

  @Mock
  VisitRepository visitRepository;

  @InjectMocks
  VisitSDJpaService service;
  Visit visit;
  Set<Visit> visits;

  @BeforeEach
  void setUp() {
    visit = new Visit();
    visits = new HashSet<>();
    visits.add(visit);
  }

  @Test
  void findAll() {
    //given
    given(visitRepository.findAll()).willReturn(visits);

    //when
    final var foundVisits = service.findAll();

    //then
    then(visitRepository).should().findAll();
    assertThat(foundVisits).hasSize(1);
  }

  @Test
  void findById() {
    //given
    given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

    //when
    final var foundVisit = service.findById(1L);

    //then
    then(visitRepository).should().findById(anyLong());
    assertThat(foundVisit).isNotNull();
  }

  @Test
  void save() {
    //given
    given(visitRepository.save(any(Visit.class))).willReturn(visit);

    //when
    final var savedVisit = service.save(new Visit());

    //then
    then(visitRepository).should().save(any(Visit.class));
    assertThat(savedVisit).isNotNull();
  }

  @Test
  void delete() {
    //given - none

    //when
    service.delete(new Visit());

    //then
    then(visitRepository).should().delete(any(Visit.class));

  }

  @Test
  void deleteById() {
    //given - none

    //when
    service.deleteById(1L);

    //then
    then(visitRepository).should().deleteById(anyLong());

  }
}
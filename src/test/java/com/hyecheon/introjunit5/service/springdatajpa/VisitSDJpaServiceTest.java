package com.hyecheon.introjunit5.service.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hyecheon.introjunit5.model.Visit;
import com.hyecheon.introjunit5.repositories.VisitRepository;
import com.hyecheon.introjunit5.service.VisitService;
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
  VisitSDJpaService visitService;
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
    when(visitRepository.findAll()).thenReturn(visits);

    final var foundVisits = visitService.findAll();

    verify(visitRepository).findAll();

    assertThat(foundVisits).hasSize(1);
  }

  @Test
  void findById() {
    when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

    final var foundVisit = visitService.findById(1L);

    verify(visitRepository).findById(anyLong());

    assertThat(foundVisit).isNotNull();
  }

  @Test
  void save() {
    when(visitRepository.save(any(Visit.class))).thenReturn(visit);

    final var savedVisit = visitService.save(new Visit());

    verify(visitRepository).save(any(Visit.class));

    assertThat(savedVisit).isNotNull();
  }

  @Test
  void delete() {

    visitService.delete(new Visit());

    verify(visitRepository).delete(any(Visit.class));

  }

  @Test
  void deleteById() {
    visitService.deleteById(1L);

    verify(visitRepository).deleteById(anyLong());

  }
}
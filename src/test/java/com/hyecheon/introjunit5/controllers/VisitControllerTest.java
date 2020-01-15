package com.hyecheon.introjunit5.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.hyecheon.introjunit5.model.Pet;
import com.hyecheon.introjunit5.service.VisitService;
import com.hyecheon.introjunit5.service.map.PetMapService;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

  @Mock
  VisitService visitService;

  @Spy
  PetMapService petService;

  @InjectMocks
  VisitController visitController;

  @Test
  void loadPetWithVisit() {
    final var model = new HashMap<String, Object>();
    final var pet = new Pet(12L);
    final var pet3 = new Pet(3L);

    petService.save(pet);
    petService.save(pet3);

    given(petService.findById(anyLong())).willCallRealMethod();

    final var visit = visitController.loadPetWithVisit(12L, model);

    assertThat(visit).isNotNull();
    assertThat(visit.getPet()).isNotNull();
    assertThat(visit.getPet().getId()).isEqualTo(12L);
    verify(petService, times(1)).findById(anyLong());
  }

  @Test
  void loadPetWithVisitWithStubbing() {
    final var model = new HashMap<String, Object>();
    final var pet = new Pet(12L);
    final var pet3 = new Pet(3L);

    petService.save(pet);
    petService.save(pet3);

    given(petService.findById(anyLong())).willReturn(pet3);

    final var visit = visitController.loadPetWithVisit(12L, model);

    assertThat(visit).isNotNull();
    assertThat(visit.getPet()).isNotNull();
    assertThat(visit.getPet().getId()).isEqualTo(3L);
    verify(petService, times(1)).findById(anyLong());
  }
}
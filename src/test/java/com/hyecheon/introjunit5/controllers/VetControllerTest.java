package com.hyecheon.introjunit5.controllers;

import com.hyecheon.introjunit5.fauxspring.Model;
import com.hyecheon.introjunit5.fauxspring.ModelMapImpl;
import com.hyecheon.introjunit5.model.Vet;
import com.hyecheon.introjunit5.service.SpecialtyService;
import com.hyecheon.introjunit5.service.VetService;
import com.hyecheon.introjunit5.service.map.SpecialityMapService;
import com.hyecheon.introjunit5.service.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest {

    VetService vetService;
    SpecialtyService specialtyService;
    VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        vetController = new VetController(vetService);

        final var vet1 = new Vet(1L, "joe", "buck", null);
        final var vet2 = new Vet(2L, "Jimmy", "Smith", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        Model model = new ModelMapImpl();
        final var view = vetController.listVets(model);
        assertThat("vets/index").isEqualTo(view);
        final Set modelAttribute = (Set) ((ModelMapImpl) model).getMap().get("vets");
        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}
package com.hyecheon.introjunit5.controllers;

import com.hyecheon.introjunit5.fauxspring.BindingResult;
import com.hyecheon.introjunit5.fauxspring.WebDataBinder;
import com.hyecheon.introjunit5.model.Pet;
import com.hyecheon.introjunit5.model.Visit;
import com.hyecheon.introjunit5.service.PetService;
import com.hyecheon.introjunit5.service.VisitService;
import java.util.Map;
import javax.validation.Valid;

public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public Visit loadPetWithVisit(Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    public String initNewVisitForm(Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);

            return "redirect:/owners/{ownerId}";
        }
    }
}
package com.hyecheon.introjunit5.controllers;

import com.hyecheon.introjunit5.fauxspring.Model;
import com.hyecheon.introjunit5.service.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
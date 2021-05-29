package com.udacity.jdnd.critter.controller;

import com.udacity.jdnd.critter.data.dto.pet.PetDTO;
import com.udacity.jdnd.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping("/{ownerId}")
    public PetDTO savePet(@PathVariable long ownerId, @RequestBody PetDTO petDTO) {

        petDTO.setOwnerId(ownerId);

        return petService.savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        return petService.getPet(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){

        return petService.getPets();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petService.getPetsByOwner(ownerId);
    }
}

package com.udacity.jdnd.critter.pet;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @PostMapping
    public com.udacity.jdnd.critter.pet.PetDTO savePet(@RequestBody com.udacity.jdnd.critter.pet.PetDTO petDTO) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public com.udacity.jdnd.critter.pet.PetDTO getPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<com.udacity.jdnd.critter.pet.PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<com.udacity.jdnd.critter.pet.PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }
}

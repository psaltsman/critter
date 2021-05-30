package com.udacity.jdnd.critter.controller;

import com.udacity.jdnd.critter.data.dto.pet.PetDTO;
import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.service.CustomerService;
import com.udacity.jdnd.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/{ownerId}")
    public PetDTO savePet(@PathVariable long ownerId, @RequestBody PetDTO petDTO) {

        petDTO.setOwnerId(ownerId);

        Pet pet = convertPetToEntity(petDTO);

        pet.setCustomer(customerService.getCustomerById(ownerId));

        return convertPetToDTO(petService.savePet(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        return convertPetToDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){

        return convertPetToList(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return convertPetToList(petService.getPetsByOwner(ownerId));
    }

    private Pet convertPetToEntity(PetDTO petDTO) {

        Pet pet = new Pet();

        BeanUtils.copyProperties(petDTO, pet);

        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());

        pet.setCustomer(customer);

        return pet;
    }

    private PetDTO convertPetToDTO(Pet pet) {

        PetDTO petDTO = new PetDTO();

        BeanUtils.copyProperties(pet, petDTO);

        Customer customer = pet.getCustomer();

        petDTO.setOwnerId(customer.getId());

        return petDTO;
    }

    private List<PetDTO> convertPetToList(List<Pet> pets) {

        ArrayList<PetDTO> toReturn = new ArrayList<>();

        for (Pet pet : pets) {

            toReturn.add(convertPetToDTO(pet));
        }

        return toReturn;
    }
}

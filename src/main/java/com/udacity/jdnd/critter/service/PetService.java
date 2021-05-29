package com.udacity.jdnd.critter.service;

import com.udacity.jdnd.critter.data.dto.pet.PetDTO;
import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.data.repository.CustomerRepository;
import com.udacity.jdnd.critter.data.repository.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
    
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public PetDTO savePet(PetDTO petDTO) {

        Pet pet = convertPetToEntity(petDTO);

        Customer customer = customerRepository.getById(petDTO.getOwnerId());

        pet.setCustomer(customer);

        pet = petRepository.save(pet);

        //Once the pet has been saved then add it to the customer entity
        List<Pet> pets = customer.getPets();

        if (pets != null)

            pets.add(pet);

        else {

            pets = new ArrayList<Pet>();
            pets.add(pet);
        }

        customer.setPets(pets);
        customerRepository.save(customer);

        return convertPetToDTO(pet);
    }

    public PetDTO getPet(Long petId) {

        Pet pet = petRepository.getById(petId);

        return convertPetToDTO(pet);
    }

    public List<PetDTO> getPets() {

        List<Pet> pets = petRepository.findAll();

        return convertPetToList(pets);
    }

    public List<PetDTO> getPetsByOwner(Long ownerId) {

        Customer customer = customerRepository.getById(ownerId);
        
        List<Pet> pets = petRepository.findByCustomer(customer);

        return convertPetToList(pets);
    }

    public Pet convertPetToEntity(PetDTO petDTO) {

        Pet pet = new Pet();

        BeanUtils.copyProperties(petDTO, pet);

        Customer customer = customerRepository.getById(petDTO.getOwnerId());

        pet.setCustomer(customer);

        return pet;
    }

    public PetDTO convertPetToDTO(Pet pet) {

        PetDTO petDTO = new PetDTO();

        BeanUtils.copyProperties(pet, petDTO);

        Customer customer = pet.getCustomer();

        petDTO.setOwnerId(customer.getId());

        return petDTO;
    }

    public List<PetDTO> convertPetToList(List<Pet> pets) {

        ArrayList<PetDTO> toReturn = new ArrayList<>();

        for (Pet pet : pets) {

            toReturn.add(convertPetToDTO(pet));
        }

        return toReturn;
    }
}

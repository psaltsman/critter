package com.udacity.jdnd.critter.service;

import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.data.repository.CustomerRepository;
import com.udacity.jdnd.critter.data.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {
    
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet getPetById(Long petId) {

        return petRepository.getById(petId);
    }

    public Pet savePet(Pet pet) {

        pet = petRepository.save(pet);

        Customer customer = pet.getCustomer();

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

        return pet;
    }

    public Pet getPet(Long petId) {

        return petRepository.getById(petId);
    }

    public List<Pet> getPets() {

        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long ownerId) {

        Customer customer = customerRepository.getById(ownerId);
        
        return petRepository.findByCustomer(customer);
    }
}

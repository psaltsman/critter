package com.udacity.jdnd.critter.service;

import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.data.repository.CustomerRepository;
import com.udacity.jdnd.critter.data.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer getCustomerById(Long customerId) {

        return customerRepository.getById(customerId);
    }

    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(Long petId) {

        Pet pet = petRepository.getById(petId);

        return pet.getCustomer();
    }
}

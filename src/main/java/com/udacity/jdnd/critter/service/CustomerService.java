package com.udacity.jdnd.critter.service;

import com.udacity.jdnd.critter.data.dto.user.CustomerDTO;
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
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        Customer customer = convertCustomerToEntity(customerDTO);

        ArrayList<Pet> pets = new ArrayList<>();

        customer.setPets(pets);

        customer = customerRepository.save(customer);

        return convertCustomerToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {

        return convertCustomerToList(customerRepository.findAll());
    }

    public CustomerDTO getOwnerByPet(Long petId) {

        Pet pet = petRepository.getById(petId);

        return convertCustomerToDTO(pet.getCustomer());
    }

    public Customer convertCustomerToEntity(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CustomerDTO convertCustomerToDTO(Customer customer) {

        System.out.println(customer.getId());

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        ArrayList<Long> petIds = new ArrayList<>();

        for (Pet pet : customer.getPets()) {

            petIds.add(pet.getId());
        }

        customerDTO.setPetIds(petIds);

        return customerDTO;
    }

    public List<CustomerDTO> convertCustomerToList(List<Customer> customers) {

        ArrayList<CustomerDTO> toReturn = new ArrayList<>();

        for (Customer customer : customers) {

            toReturn.add(convertCustomerToDTO(customer));
        }

        return toReturn;
    }
}

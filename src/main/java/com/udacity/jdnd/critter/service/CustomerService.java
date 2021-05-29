package com.udacity.jdnd.critter.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.udacity.jdnd.critter.data.dto.user.CustomerDTO;
import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        Customer customer = customerRepository.save(convertToEntity(customerDTO));

        return convertToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {

        return convertList(customerRepository.findAll());
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private CustomerDTO convertToDTO(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    private List<CustomerDTO> convertList(List<Customer> customers) {

        ArrayList<CustomerDTO> toReturn = new ArrayList<>();

        for (Customer customer : customers) {

            toReturn.add(convertToDTO(customer));
        }

        return toReturn;
    }
}

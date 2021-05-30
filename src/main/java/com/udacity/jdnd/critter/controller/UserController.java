package com.udacity.jdnd.critter.controller;

import com.udacity.jdnd.critter.data.dto.user.CustomerDTO;
import com.udacity.jdnd.critter.data.dto.user.EmployeeDTO;
import com.udacity.jdnd.critter.data.dto.user.EmployeeRequestDTO;
import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.service.CustomerService;
import com.udacity.jdnd.critter.service.EmployeeService;
import com.udacity.jdnd.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {

       Customer customer = customerService.saveCustomer(convertCustomerToEntity(customerDTO));

       return convertCustomerToDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();

        return convertCustomerToList(customers);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        return convertCustomerToDTO(customerService.getOwnerByPet(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = convertEmployeeToEntity(employeeDTO);

        return convertEmployeeToDTO(employeeService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        return convertEmployeeToDTO(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {

        List<Employee> employees = employeeService.findEmployeesForService(employeeRequestDTO);

        return convertEmployeeToList(employees);
    }

    //Converts a CustomerDTO to a Customer Entity
    private Customer convertCustomerToEntity(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        BeanUtils.copyProperties(customerDTO, customer);

        ArrayList<Pet> pets = new ArrayList<>();

        if (customerDTO.getPetIds() != null) {

            for (Long petId : customerDTO.getPetIds()) {

                Pet pet = petService.getPet(petId);

                pets.add(pet);
            }
        }

        customer.setPets(pets);

        return customer;
    }

    //Converts a Customer Entity to a CustomerDTO
    private CustomerDTO convertCustomerToDTO(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();

        BeanUtils.copyProperties(customer, customerDTO);

        ArrayList<Long> petIds = new ArrayList<>();

        if (customer.getPets() != null) {

            for (Pet pet : customer.getPets()) {

                petIds.add(pet.getId());
            }
        }

        customerDTO.setPetIds(petIds);

        return customerDTO;
    }

    //Converts a list of Customer Entities to a list of CustomerDTOs
    private List<CustomerDTO> convertCustomerToList(List<Customer> customers) {

        ArrayList<CustomerDTO> toReturn = new ArrayList<>();

        for (Customer customer : customers) {

            toReturn.add(convertCustomerToDTO(customer));
        }

        return toReturn;
    }

    //Converts and EmployeeDTO to an Employee Entity
    private Employee convertEmployeeToEntity(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setDaysAvailable(employeeDTO.getDaysAvailable());

        employee.setSkills(employeeDTO.getSkills());

        return employee;
    }

    //Converts an Employee Entity to an EmployeeDTO
    private EmployeeDTO convertEmployeeToDTO(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        BeanUtils.copyProperties(employee, employeeDTO);

        employeeDTO.setDaysAvailable(employee.getDaysAvailable());

        employeeDTO.setSkills(employee.getSkills());

        return employeeDTO;
    }

    //Converts a list of Employee Entities to a list of EmployeeDTOs
    private List<EmployeeDTO> convertEmployeeToList(List<Employee> employees) {

        ArrayList<EmployeeDTO> toReturn = new ArrayList<>();

        for (Employee employee : employees) {

            toReturn.add(convertEmployeeToDTO(employee));
        }

        return toReturn;
    }
}

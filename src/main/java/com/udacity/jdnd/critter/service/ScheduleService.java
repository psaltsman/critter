package com.udacity.jdnd.critter.service;

import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.data.entity.Schedule;
import com.udacity.jdnd.critter.data.repository.CustomerRepository;
import com.udacity.jdnd.critter.data.repository.EmployeeRepository;
import com.udacity.jdnd.critter.data.repository.PetRepository;
import com.udacity.jdnd.critter.data.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Schedule createSchedule(Schedule schedule) {

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {

        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {

        Employee employee = employeeRepository.getById(employeeId);

        return scheduleRepository.findByEmployees(employee);
    }

    public List<Schedule> getScheduleForPet(Long petId) {

        Pet pet = petRepository.getById(petId);

        return scheduleRepository.findByPets(pet);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {

        //Returned list of schedules for this customer's pets
        ArrayList<Schedule> toReturn = new ArrayList<>();

        //Get the customer entity
        Customer customer = customerRepository.getById(customerId);

        //Get all of their pets
        List<Pet> pets = customer.getPets();

        //For each pet look up their schedule and add it to the return list
        for (Pet pet : pets) {

            List<Schedule> thisPetSchedule = scheduleRepository.findByPets(pet);

            for (Schedule schedule : thisPetSchedule) {

                if (!toReturn.contains(schedule)) {

                    toReturn.add(schedule);
                }
            }
        }

        return toReturn;
    }
}

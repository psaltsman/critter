package com.udacity.jdnd.critter.service;

import com.google.common.collect.Sets;
import com.udacity.jdnd.critter.data.dto.schedule.ScheduleDTO;
import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.data.entity.Schedule;
import com.udacity.jdnd.critter.data.repository.CustomerRepository;
import com.udacity.jdnd.critter.data.repository.EmployeeRepository;
import com.udacity.jdnd.critter.data.repository.PetRepository;
import com.udacity.jdnd.critter.data.repository.ScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {

        Schedule schedule = scheduleRepository.save(convertScheduleToEntity(scheduleDTO));

        return convertScheduleToDTO(schedule);
    }

    public List<ScheduleDTO> getAllSchedules() {

        List<Schedule> schedules = scheduleRepository.findAll();

        return convertScheduleToList(schedules);
    }

    public List<ScheduleDTO> getScheduleForEmployee(Long employeeId) {

        Employee employee = employeeRepository.getById(employeeId);

        List<Schedule> schedules = scheduleRepository.findByEmployees(employee);

        return convertScheduleToList(schedules);
    }

    public List<ScheduleDTO> getScheduleForPet(Long petId) {

        Pet pet = petRepository.getById(petId);

        List<Schedule> schedules = scheduleRepository.findByPets(pet);

        return convertScheduleToList(schedules);
    }

    public List<ScheduleDTO> getScheduleForCustomer(Long customerId) {

        //Returned list of schedules for this customer's pets
        ArrayList<ScheduleDTO> toReturn = new ArrayList<>();

        //Get the customer entity
        Customer customer = customerRepository.getById(customerId);

        //Get all of their pets
        List<Pet> pets = customer.getPets();

        //For each pet look up their schedule and add it to the return list
        for (Pet pet : pets) {

            List<Schedule> thisPetSchedule = scheduleRepository.findByPets(pet);

            for (Schedule scedule : thisPetSchedule) {

                ScheduleDTO scheduleDTO = convertScheduleToDTO(scedule);

                if (!toReturn.contains(scheduleDTO)) {

                    toReturn.add(scheduleDTO);
                }
            }
        }

        return toReturn;
    }

    private Schedule convertScheduleToEntity(ScheduleDTO scheduleDTO) {

        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(scheduleDTO, schedule);

        ArrayList<Employee> employees = new ArrayList<>();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        for (Long employeeId : employeeIds) {
            Employee employee = employeeRepository.getById(employeeId);
            if (!employees.contains(employee)) {
                employees.add(employee);
            }
        }
        schedule.setEmployees(employees);

        ArrayList<Pet> pets = new ArrayList<>();
        List<Long> petIds = scheduleDTO.getPetIds();
        for (Long petId : petIds) {
            Pet pet = petRepository.getById(petId);
            if (!pets.contains(pet)) {
                pets.add(pet);
            }
        }
        schedule.setPets(pets);

        if (scheduleDTO.getActivities() != null) {
            schedule.setActivities(scheduleDTO.getActivities());
        } else {
            schedule.setActivities(Sets.newHashSet());
        }

        return schedule;
    }

    private ScheduleDTO convertScheduleToDTO(Schedule schedule) {

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        BeanUtils.copyProperties(schedule, scheduleDTO);

        ArrayList<Long> employeeIds = new ArrayList<>();
        List<Employee> employees = schedule.getEmployees();
        for (Employee employee : employees) {
            Long employeeId = employee.getId();
            if (!employeeIds.contains(employeeId)) {
                employeeIds.add(employeeId);
            }
        }
        scheduleDTO.setEmployeeIds(employeeIds);

        ArrayList<Long> petIds = new ArrayList<>();
        List<Pet> pets = schedule.getPets();
        for (Pet pet : pets) {
            Long petId = pet.getId();
            if (!petIds.contains(petId)) {
                petIds.add(petId);
            }
        }
        scheduleDTO.setPetIds(petIds);

        if (schedule.getActivities() != null) {
            scheduleDTO.setActivities(schedule.getActivities());
        } else {
            scheduleDTO.setActivities(Sets.newHashSet());
        }

        return scheduleDTO;
    }

    private List<ScheduleDTO> convertScheduleToList(List<Schedule> schedules) {

        ArrayList<ScheduleDTO> toReturn = new ArrayList<>();

        for (Schedule schedule : schedules) {

            toReturn.add(convertScheduleToDTO(schedule));
        }

        return toReturn;
    }
}

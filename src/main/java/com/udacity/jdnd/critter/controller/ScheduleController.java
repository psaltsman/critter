package com.udacity.jdnd.critter.controller;

import com.google.common.collect.Sets;
import com.udacity.jdnd.critter.data.dto.schedule.ScheduleDTO;
import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.entity.Pet;
import com.udacity.jdnd.critter.data.entity.Schedule;
import com.udacity.jdnd.critter.service.EmployeeService;
import com.udacity.jdnd.critter.service.PetService;
import com.udacity.jdnd.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        Schedule schedule = convertScheduleToEntity(scheduleDTO);

        return convertScheduleToDTO(scheduleService.createSchedule(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        return convertScheduleToList(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        return convertScheduleToList(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

        return convertScheduleToList(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

        return convertScheduleToList(scheduleService.getScheduleForCustomer(customerId));
    }

    private Schedule convertScheduleToEntity(ScheduleDTO scheduleDTO) {

        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(scheduleDTO, schedule);

        ArrayList<Employee> employees = new ArrayList<>();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        for (Long employeeId : employeeIds) {
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (!employees.contains(employee)) {
                employees.add(employee);
            }
        }
        schedule.setEmployees(employees);

        ArrayList<Pet> pets = new ArrayList<>();
        List<Long> petIds = scheduleDTO.getPetIds();
        for (Long petId : petIds) {
            Pet pet = petService.getPetById(petId);
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

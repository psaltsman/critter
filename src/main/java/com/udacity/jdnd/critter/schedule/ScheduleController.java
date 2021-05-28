package com.udacity.jdnd.critter.schedule;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @PostMapping
    public com.udacity.jdnd.critter.schedule.ScheduleDTO createSchedule(@RequestBody com.udacity.jdnd.critter.schedule.ScheduleDTO scheduleDTO) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<com.udacity.jdnd.critter.schedule.ScheduleDTO> getAllSchedules() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<com.udacity.jdnd.critter.schedule.ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<com.udacity.jdnd.critter.schedule.ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<com.udacity.jdnd.critter.schedule.ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
}

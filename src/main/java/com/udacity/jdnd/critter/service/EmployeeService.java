package com.udacity.jdnd.critter.service;

import com.udacity.jdnd.critter.data.dto.user.EmployeeRequestDTO;
import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.enums.EmployeeSkill;
import com.udacity.jdnd.critter.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long employeeId) {

        return employeeRepository.getById(employeeId);
    }

    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {

        return employeeRepository.getById(id);
    }

    public void setAvailability(Set<DayOfWeek> availableDays, Long employeeId) {

        Employee employee = employeeRepository.getById(employeeId);

        employee.setDaysAvailable(availableDays);

        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {

        DayOfWeek dayAvailable = employeeRequestDTO.getDate().getDayOfWeek();

        Set<EmployeeSkill> employeeSkills = employeeRequestDTO.getSkills();

        ArrayList<Employee> toReturn = new ArrayList<>();

        List<Employee> availableEmployees = employeeRepository.findByDaysAvailableAndSkillsIn(dayAvailable, employeeSkills);

        for (Employee employee : availableEmployees) {

            //Only add the employees that have all of the requested skills for the requested day
            if (employee.getSkills().containsAll(employeeSkills)) {

                if (!toReturn.contains(employee)) {
                    toReturn.add(employee);
                }
            }
        }

        return toReturn;
    }
}

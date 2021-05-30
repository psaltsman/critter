package com.udacity.jdnd.critter.service;

import com.google.common.collect.Sets;
import com.udacity.jdnd.critter.data.dto.user.CustomerDTO;
import com.udacity.jdnd.critter.data.dto.user.EmployeeDTO;
import com.udacity.jdnd.critter.data.dto.user.EmployeeRequestDTO;
import com.udacity.jdnd.critter.data.entity.Customer;
import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.enums.EmployeeSkill;
import com.udacity.jdnd.critter.data.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        Employee employee = employeeRepository.save(convertEmployeeToEntity(employeeDTO));

        return convertEmployeeToDTO(employee);
    }

    public EmployeeDTO getEmployee(Long id) {

        return convertEmployeeToDTO(employeeRepository.getById(id));
    }

    public void setAvailability(Set<DayOfWeek> availableDays, Long employeeId) {

        Employee employee = employeeRepository.getById(employeeId);

        employee.setDaysAvailable(availableDays);

        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {

        DayOfWeek dayAvailable = employeeRequestDTO.getDate().getDayOfWeek();

        Set<EmployeeSkill> employeeSkills = employeeRequestDTO.getSkills();

        ArrayList<EmployeeDTO> toReturn = new ArrayList<>();

        List<Employee> availableEmployees = employeeRepository.findByDaysAvailableAndSkillsIn(dayAvailable, employeeSkills);

        for (Employee employee : availableEmployees) {

            //Only add the employees that have all of the requested skills for the requested day
            if (employee.getSkills().containsAll(employeeSkills)) {

                toReturn.add(convertEmployeeToDTO(employee));
            }
        }

        return toReturn;
    }

    public Employee convertEmployeeToEntity(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setSkills(employeeDTO.getSkills());

        return employee;
    }

    public EmployeeDTO convertEmployeeToDTO(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        BeanUtils.copyProperties(employee, employeeDTO);

        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        employeeDTO.setSkills(employee.getSkills());

        return employeeDTO;
    }

    public List<EmployeeDTO> convertEmployeeToList(List<Employee> employees) {

        ArrayList<EmployeeDTO> toReturn = new ArrayList<>();

        for (Employee employee : employees) {

            toReturn.add(convertEmployeeToDTO(employee));
        }

        return toReturn;
    }
}

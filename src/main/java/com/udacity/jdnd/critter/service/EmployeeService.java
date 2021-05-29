package com.udacity.jdnd.critter.service;

import com.udacity.jdnd.critter.data.dto.user.EmployeeDTO;
import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Employee convertEmployeeToEntity(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public EmployeeDTO convertEmployeeToDTO(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }
}

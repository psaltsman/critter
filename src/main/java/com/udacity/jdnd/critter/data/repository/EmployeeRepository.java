package com.udacity.jdnd.critter.data.repository;

import com.udacity.jdnd.critter.data.entity.Employee;
import com.udacity.jdnd.critter.data.enums.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDaysAvailableAndSkillsIn(DayOfWeek dayAvailable, Set<EmployeeSkill> skills);
}

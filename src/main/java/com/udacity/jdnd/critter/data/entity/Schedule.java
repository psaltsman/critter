package com.udacity.jdnd.critter.data.entity;

import com.udacity.jdnd.critter.data.enums.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Schedule(Long id, List<Employee> employees, List<Pet> pets, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id.equals(schedule.id) && employees.equals(schedule.employees) && pets.equals(schedule.pets) && date.equals(schedule.date) && activities.equals(schedule.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employees, pets, date, activities);
    }
}

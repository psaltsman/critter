package com.udacity.jdnd.critter.data.dto.schedule;

import com.udacity.jdnd.critter.data.enums.EmployeeSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
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
        ScheduleDTO that = (ScheduleDTO) o;
        return id == that.id && employeeIds.equals(that.employeeIds) && petIds.equals(that.petIds) && date.equals(that.date) && activities.equals(that.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeIds, petIds, date, activities);
    }
}

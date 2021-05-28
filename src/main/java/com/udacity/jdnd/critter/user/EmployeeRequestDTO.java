package com.udacity.jdnd.critter.user;

import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    private Set<com.udacity.jdnd.critter.user.EmployeeSkill> skills;
    private LocalDate date;

    public Set<com.udacity.jdnd.critter.user.EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<com.udacity.jdnd.critter.user.EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

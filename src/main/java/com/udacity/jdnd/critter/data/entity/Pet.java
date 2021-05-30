package com.udacity.jdnd.critter.data.entity;

import com.udacity.jdnd.critter.data.enums.PetType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private PetType type;
    private String name;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    private LocalDate birthDate;
    private String notes;

    public Pet() {
    }

    public Pet(Long id, PetType type, String name, Customer customer, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.customer = customer;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id.equals(pet.id) && type == pet.type && name.equals(pet.name) && customer.equals(pet.customer) && birthDate.equals(pet.birthDate) && notes.equals(pet.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, customer, birthDate, notes);
    }
}

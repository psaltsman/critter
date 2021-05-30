package com.udacity.jdnd.critter.data.dto.pet;

import com.udacity.jdnd.critter.data.enums.PetType;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

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

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetDTO petDTO = (PetDTO) o;
        return id == petDTO.id && ownerId == petDTO.ownerId && type == petDTO.type && name.equals(petDTO.name) && birthDate.equals(petDTO.birthDate) && notes.equals(petDTO.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, ownerId, birthDate, notes);
    }
}

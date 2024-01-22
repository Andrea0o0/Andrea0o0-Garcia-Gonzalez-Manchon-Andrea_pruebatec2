package com.practicalexercises.assessment2.logic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "citizen", schema = "shift_operator")
public class Citizen extends Person implements Serializable {
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;

    // CascadeType is used to always link information from within the relationship, if you modify the shift it will also be modified within the citizen list.
    // OrphanRemoval, it is in case the citizen has been removed or the turn within the list, then the citizens turn and the turn in the database will be removed.
//    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "citizen")
//    @JoinColumn(name = "shifts_citizen")
    private List<Shift> shifts;

    public Citizen() {
    }

    public Citizen(String name, String lastName, LocalDate dateOfBirth, List<Shift> shifts, Long id, String username, String password) {
        super(id, username, password);
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.shifts = shifts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
    
    
    @Override
    public String toString() {
        return "Citizen: \n" +
                "Id -----------> " + getId() + "\n" + 
                "Username -----> " + getUsername() + "\n" +
                "Password -----> " + getPassword() + "\n" +
                "Name ---------> " + name + "\n" +
                "Last Name ----> " + lastName + "\n" +
                "Birth Date ---> " + dateOfBirth + "\n" +
                "Shifts -------> " + shifts;
    }
    
}

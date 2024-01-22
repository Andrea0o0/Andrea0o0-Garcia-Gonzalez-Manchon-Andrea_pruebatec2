package com.practicalexercises.assessment2.logic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "procedure_entity", schema = "shift_operator")
public class ProcedureEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "requirements", length = 1000)
    private String requirements;

    @ManyToOne
    @JoinColumn(name = "admin")
    private Administrator admin;

    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "shifts_procedure")
    private List<Shift> shifts;

    public ProcedureEntity() {
    }

    public ProcedureEntity(Long id, String title, String description, String requirements, Administrator admin, List<Shift> shifts) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.admin = admin;
        this.shifts = shifts;
    }

    public ProcedureEntity(String title, String description, String requirements, Administrator admin, List<Shift> shifts) {
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.admin = admin;
        this.shifts = shifts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    @Override
    public String toString() {
        return "Procedure: \n" + 
                "Id -----------> " + id + "\n" + 
                "Title --------> " + title + "\n" +
                "Description --> " + description + "\n" +
                "Requirements -> " + requirements + "\n" +
                "Admin --------> " + (admin != null ? admin.getUsername() : null) + "\n" +
                "Shifts -------> " + shifts;
    }    
    
    
    
}

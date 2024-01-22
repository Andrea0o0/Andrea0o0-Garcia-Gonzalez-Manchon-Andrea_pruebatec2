package com.practicalexercises.assessment2.logic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "adminstrator", schema = "shift_operator")
public class Administrator extends Person implements Serializable {
    // CascadeType is used to always link information from within the relationship, if you modify the procedure it will also be modified within the admin list.
    @OneToMany(mappedBy = "admin")
//    @JoinColumn(name = "procedures_admin")
    private List<ProcedureEntity> procedures;

    public Administrator() {
    }

    public Administrator(List<ProcedureEntity> procedures, Long id, String username, String password) {
        super(id, username, password);
        this.procedures = procedures;
    }

    public List<ProcedureEntity> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureEntity> procedures) {
        this.procedures = procedures;
    }

    @Override
    public String toString() {
        return "Admin: \n" +
                "Id -----------> " + getId() + "\n" +
                "Username -----> " + getUsername() + "\n" +
                "Password -----> " + getPassword() + "\n" +
                "Procedures ---> " + procedures;
    }  
    
}
package com.practicalexercises.assessment2.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Table;

@Entity
@Table(name = "shifts", schema = "shift_operator")
public class Shift implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_hour")
    private LocalDateTime dateHour;

    private boolean shiftStatus;
    private String additionalInformation;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private ProcedureEntity procedure;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    public Shift() {
    }

    public Shift(Long id, LocalDateTime dateHour, boolean shiftStatus, String additionalInformation, ProcedureEntity procedure, Citizen citizen) {
        this.id = id;
        this.dateHour = dateHour;
        this.shiftStatus = shiftStatus;
        this.additionalInformation = additionalInformation;
        this.procedure = procedure;
        this.citizen = citizen;
    }

    public Shift(LocalDateTime dateHour, boolean shiftStatus, String additionalInformation, ProcedureEntity procedure, Citizen citizen) {
        this.dateHour = dateHour;
        this.shiftStatus = shiftStatus;
        this.additionalInformation = additionalInformation;
        this.procedure = procedure;
        this.citizen = citizen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(LocalDateTime dateHour) {
        this.dateHour = dateHour;
    }

    public boolean isShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(boolean shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public ProcedureEntity getProcedure() {
        return procedure;
    }

    public void setProcedure(ProcedureEntity procedure) {
        this.procedure = procedure;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public String toString() {
        return "Shift: \n" + 
                "Id -----------> " + id + "\n" +
                "Date Hour ----> " + dateHour + "\n" +
                "Status -------> " + shiftStatus + "\n" +
                "Addit Info ---> " + additionalInformation + "\n" +
                "Procedure ----> " + procedure + "\n" +
                "Citizen ------> " + citizen;
    }    
    
}

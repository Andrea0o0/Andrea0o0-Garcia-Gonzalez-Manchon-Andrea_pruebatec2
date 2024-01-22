package com.practicalexercises.assessment2.persistence;

import com.practicalexercises.assessment2.logic.Administrator;
import com.practicalexercises.assessment2.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.practicalexercises.assessment2.logic.Citizen;
import com.practicalexercises.assessment2.logic.Person;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.logic.Shift;


public class PersistenceController {
    
    AdministratorJpaController adminJpa = new AdministratorJpaController();
    ProcedureEntityJpaController procedureJpa = new ProcedureEntityJpaController();
    ShiftJpaController shiftJpa = new ShiftJpaController();
    CitizenJpaController citizenJpa = new CitizenJpaController();
    PersonJpaController personJpa = new PersonJpaController();
    
    
    // ADMIN ----------------------------------------------------------------------------------------
    public void createAdmin(Administrator admin) {
        adminJpa.create(admin);
    }

    public List<Administrator> bringAdmins() {
        return adminJpa.findAdministratorEntities();
    }

    public void deleteAdmin(Long idAdminDelete) {
        try {
            citizenJpa.destroy(idAdminDelete);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Administrator searchAdmin(Long idAdmin) {
        return adminJpa.findAdministrator(idAdmin);
    }

    public void modifyAdmin(Administrator admin) {
        try {
            adminJpa.edit(admin);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //  ---------------------------------------------------------------------------------------------
    
    // PROCEDURE ------------------------------------------------------------------------------------
    public void createProcedure(ProcedureEntity procedure) {
        procedureJpa.create(procedure);
    }

    public List<ProcedureEntity> bringProcedures() {
        return procedureJpa.findProcedureEntityEntities();
    }

    public void deleteProcedure(Long idProcedureDelete) {
        try {
            procedureJpa.destroy(idProcedureDelete);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ProcedureEntity searchProcedure(Long idProcedure) {
        return procedureJpa.findProcedureEntity(idProcedure);
    }

    public void modifyProcedure(ProcedureEntity procedure) {
        try {
            procedureJpa.edit(procedure);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //  ---------------------------------------------------------------------------------------------
    
    // SHIFT ----------------------------------------------------------------------------------------
    public void createShift(Shift shift) {
        shiftJpa.create(shift);
    }

    public List<Shift> bringShifts() {
        return shiftJpa.findShiftEntities();
    }

    public void deleteShift(Long idShiftDelete) {
        try {
            shiftJpa.destroy(idShiftDelete);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Shift searchShift(Long idShift) {
        return shiftJpa.findShift(idShift);
    }

    public void modifyShift(Shift shift) {
        try {
            shiftJpa.edit(shift);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //  ---------------------------------------------------------------------------------------------
    
    // CITIZEN --------------------------------------------------------------------------------------
    public void createCitizen(Citizen citizen) {
        citizenJpa.create(citizen);
    }

    public List<Citizen> bringCitizens() {
        return citizenJpa.findCitizenEntities();
    }

    public void deleteCitizen(Long idCitizenDelete) {
        try {
            citizenJpa.destroy(idCitizenDelete);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Citizen searchCitizen(Long idCitizen) {
        return citizenJpa.findCitizen(idCitizen);
    }

    public void modifyCitizen(Citizen citizen) {
        try {
            citizenJpa.edit(citizen);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //  ---------------------------------------------------------------------------------------------

    public void createPerson(Person person) {
        personJpa.create(person);
    }

}

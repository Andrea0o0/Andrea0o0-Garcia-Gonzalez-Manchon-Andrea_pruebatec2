package com.practicalexercises.assessment2.logic;

import com.practicalexercises.assessment2.persistence.PersistenceController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class Controller {
    PersistenceController persisController = new PersistenceController();
    
    // ADMIN ----------------------------------------------------------------------------------------
    public void createAdmin(List<ProcedureEntity> procedures, String username, String password){
        Administrator admin = new Administrator(procedures, Long.MIN_VALUE, username, password);
        persisController.createAdmin(admin);
    }
    
    public List<Administrator> bringAdmins(){
        return persisController.bringAdmins();
    }
    
    public void deleteAdmin(Long idDelete){
        persisController.deleteAdmin(idDelete);
    }
    
    public Administrator searchAdmin(Long idEdit){
        return persisController.searchAdmin(idEdit);
    }
    
    public Administrator searchAdminByUserPassword(String username, String password) {
        List<Administrator> admins = bringAdmins();
        Optional<Administrator> foundAdmin = admins.stream()
                .filter(admin -> admin.getUsername().equalsIgnoreCase(username) && admin.getPassword().equals(password))
                .findFirst();
        return foundAdmin.orElse(null);
    }
    
    public void modifyAdmin(List<ProcedureEntity> procedures, String username, String password, Administrator admin){
        admin.setProcedures(procedures);
        admin.setUsername(username);
        admin.setPassword(password);
        persisController.modifyAdmin(admin);
    }
    //  ---------------------------------------------------------------------------------------------
 
    // PROCEDURE ------------------------------------------------------------------------------------
    public void createProcedure(String title, String description, String requirements, Administrator admin, List<Shift> shifts){
        persisController.createProcedure(new ProcedureEntity(title, description, requirements, admin, shifts));
    }
    
    public List<ProcedureEntity> bringProcedures(){
        return persisController.bringProcedures();
    }
    
    public void deleteProcedure(Long idDelete){
        persisController.deleteProcedure(idDelete);
    }
    
    public ProcedureEntity searchProcedure(Long idEdit){
        return persisController.searchProcedure(idEdit);
    }
    
    public void modifyProcedure(String title, String description, String requirements, Administrator admin, List<Shift> shifts, ProcedureEntity procedure){
        procedure.setTitle(title);
        procedure.setDescription(description);
        procedure.setRequirements(requirements);
        procedure.setAdmin(admin);
        procedure.setShifts(shifts);
        persisController.modifyProcedure(procedure);
    }
    //  ---------------------------------------------------------------------------------------------
    
    // SHIFT ----------------------------------------------------------------------------------------
    public void createShift(LocalDateTime dateHour, boolean shiftStatus, String additionalInformation, ProcedureEntity procedure, Citizen citizen){
        persisController.createShift(new Shift(dateHour, shiftStatus, additionalInformation, procedure, citizen));
    }
    
    public List<Shift> bringShifts(){
        return persisController.bringShifts();
    }
    
    public void deleteShift(Long idDelete){
        persisController.deleteShift(idDelete);
    }
    
    public Shift searchShift(Long idEdit){
        return persisController.searchShift(idEdit);
    }
    
    public void modifyShift(LocalDateTime dateHour, boolean shiftStatus, String additionalInformation, ProcedureEntity procedure, Citizen citizen, Shift shift){
        shift.setDateHour(dateHour);
        shift.setShiftStatus(shiftStatus);
        shift.setAdditionalInformation(additionalInformation);
        shift.setProcedure(procedure);
        shift.setCitizen(citizen);
        persisController.modifyShift(shift);
    }
    //  ---------------------------------------------------------------------------------------------

 
    // CITIZEN ----------------------------------------------------------------------------------------
    public void createCitizen(String name, String lastName, LocalDate dateOfBirth, List<Shift> shifts, String username, String password){
        Citizen citizen = new Citizen(name, lastName, dateOfBirth, shifts, Long.MIN_VALUE, username, password);
        persisController.createCitizen(citizen);
    }
    
    public Citizen searchCitizenByUserPassword(String username, String password) {
        List<Citizen> citizens = bringCitizens();
        Optional<Citizen> foundCitizen = citizens.stream()
                .filter(citizen -> citizen.getUsername().equalsIgnoreCase(username) && citizen.getPassword().equals(password))
                .findFirst();
        return foundCitizen.orElse(null);
    }
    
    public List<Citizen> bringCitizens(){
        return persisController.bringCitizens();
    }
    
    public void deleteCitizen(Long idDelete){
        persisController.deleteCitizen(idDelete);
    }
    
    public Citizen searchCitizen(Long idEdit){
        return persisController.searchCitizen(idEdit);
    }
    
    public void modifyCitizen(String name, String lastName, LocalDate dateOfBirth, List<Shift> shifts, String username, String password, Citizen citizen){
        citizen.setName(name);
        citizen.setLastName(lastName);
        citizen.setDateOfBirth(dateOfBirth);
        citizen.setShifts(shifts);
        citizen.setUsername(username);
        citizen.setPassword(password);
        persisController.modifyCitizen(citizen);
    }
    //  ---------------------------------------------------------------------------------------------

    public void createPerson(String username, String password) {
        persisController.createPerson(new Person(Long.MIN_VALUE, username, password));
    }
    
}

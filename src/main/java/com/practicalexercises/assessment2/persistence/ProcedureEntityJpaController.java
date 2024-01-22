package com.practicalexercises.assessment2.persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.practicalexercises.assessment2.logic.Administrator;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.logic.Shift;
import com.practicalexercises.assessment2.persistence.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;


public class ProcedureEntityJpaController implements Serializable {

    public ProcedureEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProcedureEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("Assessment2Jpa");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcedureEntity procedureEntity) {
        if (procedureEntity.getShifts() == null) {
            procedureEntity.setShifts(new ArrayList<Shift>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrator admin = procedureEntity.getAdmin();
            if (admin != null) {
                admin = em.getReference(admin.getClass(), admin.getId());
                procedureEntity.setAdmin(admin);
            }
            List<Shift> attachedShifts = new ArrayList<Shift>();
            for (Shift shiftsShiftToAttach : procedureEntity.getShifts()) {
                shiftsShiftToAttach = em.getReference(shiftsShiftToAttach.getClass(), shiftsShiftToAttach.getId());
                attachedShifts.add(shiftsShiftToAttach);
            }
            procedureEntity.setShifts(attachedShifts);
            em.persist(procedureEntity);
            if (admin != null) {
                admin.getProcedures().add(procedureEntity);
                admin = em.merge(admin);
            }
            for (Shift shiftsShift : procedureEntity.getShifts()) {
                ProcedureEntity oldProcedureOfShiftsShift = shiftsShift.getProcedure();
                shiftsShift.setProcedure(procedureEntity);
                shiftsShift = em.merge(shiftsShift);
                if (oldProcedureOfShiftsShift != null) {
                    oldProcedureOfShiftsShift.getShifts().remove(shiftsShift);
                    oldProcedureOfShiftsShift = em.merge(oldProcedureOfShiftsShift);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcedureEntity procedureEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcedureEntity persistentProcedureEntity = em.find(ProcedureEntity.class, procedureEntity.getId());
            Administrator adminOld = persistentProcedureEntity.getAdmin();
            Administrator adminNew = procedureEntity.getAdmin();
            List<Shift> shiftsOld = persistentProcedureEntity.getShifts();
            List<Shift> shiftsNew = procedureEntity.getShifts();
            if (adminNew != null) {
                adminNew = em.getReference(adminNew.getClass(), adminNew.getId());
                procedureEntity.setAdmin(adminNew);
            }
            List<Shift> attachedShiftsNew = new ArrayList<Shift>();
            for (Shift shiftsNewShiftToAttach : shiftsNew) {
                shiftsNewShiftToAttach = em.getReference(shiftsNewShiftToAttach.getClass(), shiftsNewShiftToAttach.getId());
                attachedShiftsNew.add(shiftsNewShiftToAttach);
            }
            shiftsNew = attachedShiftsNew;
            procedureEntity.setShifts(shiftsNew);
            procedureEntity = em.merge(procedureEntity);
            if (adminOld != null && !adminOld.equals(adminNew)) {
                adminOld.getProcedures().remove(procedureEntity);
                adminOld = em.merge(adminOld);
            }
            if (adminNew != null && !adminNew.equals(adminOld)) {
                adminNew.getProcedures().add(procedureEntity);
                adminNew = em.merge(adminNew);
            }
            for (Shift shiftsOldShift : shiftsOld) {
                if (!shiftsNew.contains(shiftsOldShift)) {
                    shiftsOldShift.setProcedure(null);
                    shiftsOldShift = em.merge(shiftsOldShift);
                }
            }
            for (Shift shiftsNewShift : shiftsNew) {
                if (!shiftsOld.contains(shiftsNewShift)) {
                    ProcedureEntity oldProcedureOfShiftsNewShift = shiftsNewShift.getProcedure();
                    shiftsNewShift.setProcedure(procedureEntity);
                    shiftsNewShift = em.merge(shiftsNewShift);
                    if (oldProcedureOfShiftsNewShift != null && !oldProcedureOfShiftsNewShift.equals(procedureEntity)) {
                        oldProcedureOfShiftsNewShift.getShifts().remove(shiftsNewShift);
                        oldProcedureOfShiftsNewShift = em.merge(oldProcedureOfShiftsNewShift);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = procedureEntity.getId();
                if (findProcedureEntity(id) == null) {
                    throw new NonexistentEntityException("The procedureEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcedureEntity procedureEntity;
            try {
                procedureEntity = em.getReference(ProcedureEntity.class, id);
                procedureEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procedureEntity with id " + id + " no longer exists.", enfe);
            }
            Administrator admin = procedureEntity.getAdmin();
            if (admin != null) {
                admin.getProcedures().remove(procedureEntity);
                admin = em.merge(admin);
            }
            List<Shift> shifts = procedureEntity.getShifts();
            for (Shift shiftsShift : shifts) {
                shiftsShift.setProcedure(null);
                shiftsShift = em.merge(shiftsShift);
            }
            em.remove(procedureEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProcedureEntity> findProcedureEntityEntities() {
        return findProcedureEntityEntities(true, -1, -1);
    }

    public List<ProcedureEntity> findProcedureEntityEntities(int maxResults, int firstResult) {
        return findProcedureEntityEntities(false, maxResults, firstResult);
    }

    private List<ProcedureEntity> findProcedureEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProcedureEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProcedureEntity findProcedureEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProcedureEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcedureEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProcedureEntity> rt = cq.from(ProcedureEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

package com.practicalexercises.assessment2.persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.logic.Citizen;
import com.practicalexercises.assessment2.logic.Shift;
import com.practicalexercises.assessment2.persistence.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import javax.persistence.Persistence;


public class ShiftJpaController implements Serializable {

    public ShiftJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ShiftJpaController() {
        emf = Persistence.createEntityManagerFactory("Assessment2Jpa");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Shift shift) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcedureEntity procedure = shift.getProcedure();
            if (procedure != null) {
                procedure = em.getReference(procedure.getClass(), procedure.getId());
                shift.setProcedure(procedure);
            }
            Citizen citizen = shift.getCitizen();
            if (citizen != null) {
                citizen = em.getReference(citizen.getClass(), citizen.getId());
                shift.setCitizen(citizen);
            }
            em.persist(shift);
            if (procedure != null) {
                procedure.getShifts().add(shift);
                procedure = em.merge(procedure);
            }
            if (citizen != null) {
                citizen.getShifts().add(shift);
                citizen = em.merge(citizen);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Shift shift) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Shift persistentShift = em.find(Shift.class, shift.getId());
            ProcedureEntity procedureOld = persistentShift.getProcedure();
            ProcedureEntity procedureNew = shift.getProcedure();
            Citizen citizenOld = persistentShift.getCitizen();
            Citizen citizenNew = shift.getCitizen();
            if (procedureNew != null) {
                procedureNew = em.getReference(procedureNew.getClass(), procedureNew.getId());
                shift.setProcedure(procedureNew);
            }
            if (citizenNew != null) {
                citizenNew = em.getReference(citizenNew.getClass(), citizenNew.getId());
                shift.setCitizen(citizenNew);
            }
            shift = em.merge(shift);
            if (procedureOld != null && !procedureOld.equals(procedureNew)) {
                procedureOld.getShifts().remove(shift);
                procedureOld = em.merge(procedureOld);
            }
            if (procedureNew != null && !procedureNew.equals(procedureOld)) {
                procedureNew.getShifts().add(shift);
                procedureNew = em.merge(procedureNew);
            }
            if (citizenOld != null && !citizenOld.equals(citizenNew)) {
                citizenOld.getShifts().remove(shift);
                citizenOld = em.merge(citizenOld);
            }
            if (citizenNew != null && !citizenNew.equals(citizenOld)) {
                citizenNew.getShifts().add(shift);
                citizenNew = em.merge(citizenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = shift.getId();
                if (findShift(id) == null) {
                    throw new NonexistentEntityException("The shift with id " + id + " no longer exists.");
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
            Shift shift;
            try {
                shift = em.getReference(Shift.class, id);
                shift.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The shift with id " + id + " no longer exists.", enfe);
            }
            ProcedureEntity procedure = shift.getProcedure();
            if (procedure != null) {
                procedure.getShifts().remove(shift);
                procedure = em.merge(procedure);
            }
            Citizen citizen = shift.getCitizen();
            if (citizen != null) {
                citizen.getShifts().remove(shift);
                citizen = em.merge(citizen);
            }
            em.remove(shift);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Shift> findShiftEntities() {
        return findShiftEntities(true, -1, -1);
    }

    public List<Shift> findShiftEntities(int maxResults, int firstResult) {
        return findShiftEntities(false, maxResults, firstResult);
    }

    private List<Shift> findShiftEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Shift.class));
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

    public Shift findShift(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Shift.class, id);
        } finally {
            em.close();
        }
    }

    public int getShiftCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Shift> rt = cq.from(Shift.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

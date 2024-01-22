package com.practicalexercises.assessment2.persistence;

import com.practicalexercises.assessment2.logic.Citizen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.practicalexercises.assessment2.logic.Shift;
import com.practicalexercises.assessment2.persistence.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;


public class CitizenJpaController implements Serializable {

    public CitizenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public CitizenJpaController() {
        emf = Persistence.createEntityManagerFactory("Assessment2Jpa");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Citizen citizen) {
        if (citizen.getShifts() == null) {
            citizen.setShifts(new ArrayList<Shift>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Shift> attachedShifts = new ArrayList<Shift>();
            for (Shift shiftsShiftToAttach : citizen.getShifts()) {
                shiftsShiftToAttach = em.getReference(shiftsShiftToAttach.getClass(), shiftsShiftToAttach.getId());
                attachedShifts.add(shiftsShiftToAttach);
            }
            citizen.setShifts(attachedShifts);
            em.persist(citizen);
            for (Shift shiftsShift : citizen.getShifts()) {
                Citizen oldCitizenOfShiftsShift = shiftsShift.getCitizen();
                shiftsShift.setCitizen(citizen);
                shiftsShift = em.merge(shiftsShift);
                if (oldCitizenOfShiftsShift != null) {
                    oldCitizenOfShiftsShift.getShifts().remove(shiftsShift);
                    oldCitizenOfShiftsShift = em.merge(oldCitizenOfShiftsShift);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Citizen citizen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citizen persistentCitizen = em.find(Citizen.class, citizen.getId());
            List<Shift> shiftsOld = persistentCitizen.getShifts();
            List<Shift> shiftsNew = citizen.getShifts();
            List<Shift> attachedShiftsNew = new ArrayList<Shift>();
            for (Shift shiftsNewShiftToAttach : shiftsNew) {
                shiftsNewShiftToAttach = em.getReference(shiftsNewShiftToAttach.getClass(), shiftsNewShiftToAttach.getId());
                attachedShiftsNew.add(shiftsNewShiftToAttach);
            }
            shiftsNew = attachedShiftsNew;
            citizen.setShifts(shiftsNew);
            citizen = em.merge(citizen);
            for (Shift shiftsOldShift : shiftsOld) {
                if (!shiftsNew.contains(shiftsOldShift)) {
                    shiftsOldShift.setCitizen(null);
                    shiftsOldShift = em.merge(shiftsOldShift);
                }
            }
            for (Shift shiftsNewShift : shiftsNew) {
                if (!shiftsOld.contains(shiftsNewShift)) {
                    Citizen oldCitizenOfShiftsNewShift = shiftsNewShift.getCitizen();
                    shiftsNewShift.setCitizen(citizen);
                    shiftsNewShift = em.merge(shiftsNewShift);
                    if (oldCitizenOfShiftsNewShift != null && !oldCitizenOfShiftsNewShift.equals(citizen)) {
                        oldCitizenOfShiftsNewShift.getShifts().remove(shiftsNewShift);
                        oldCitizenOfShiftsNewShift = em.merge(oldCitizenOfShiftsNewShift);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = citizen.getId();
                if (findCitizen(id) == null) {
                    throw new NonexistentEntityException("The citizen with id " + id + " no longer exists.");
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
            Citizen citizen;
            try {
                citizen = em.getReference(Citizen.class, id);
                citizen.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citizen with id " + id + " no longer exists.", enfe);
            }
            List<Shift> shifts = citizen.getShifts();
            for (Shift shiftsShift : shifts) {
                shiftsShift.setCitizen(null);
                shiftsShift = em.merge(shiftsShift);
            }
            em.remove(citizen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Citizen> findCitizenEntities() {
        return findCitizenEntities(true, -1, -1);
    }

    public List<Citizen> findCitizenEntities(int maxResults, int firstResult) {
        return findCitizenEntities(false, maxResults, firstResult);
    }

    private List<Citizen> findCitizenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Citizen.class));
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

    public Citizen findCitizen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Citizen.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitizenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Citizen> rt = cq.from(Citizen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

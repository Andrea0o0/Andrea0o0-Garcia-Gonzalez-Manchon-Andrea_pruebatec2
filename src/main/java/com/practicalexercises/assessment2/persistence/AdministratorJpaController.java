package com.practicalexercises.assessment2.persistence;

import com.practicalexercises.assessment2.logic.Administrator;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import com.practicalexercises.assessment2.persistence.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;


public class AdministratorJpaController implements Serializable {

    public AdministratorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public AdministratorJpaController() {
        emf = Persistence.createEntityManagerFactory("Assessment2Jpa");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrator administrator) {
        if (administrator.getProcedures() == null) {
            administrator.setProcedures(new ArrayList<ProcedureEntity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProcedureEntity> attachedProcedures = new ArrayList<ProcedureEntity>();
            for (ProcedureEntity proceduresProcedureEntityToAttach : administrator.getProcedures()) {
                proceduresProcedureEntityToAttach = em.getReference(proceduresProcedureEntityToAttach.getClass(), proceduresProcedureEntityToAttach.getId());
                attachedProcedures.add(proceduresProcedureEntityToAttach);
            }
            administrator.setProcedures(attachedProcedures);
            em.persist(administrator);
            for (ProcedureEntity proceduresProcedureEntity : administrator.getProcedures()) {
                Administrator oldAdminOfProceduresProcedureEntity = proceduresProcedureEntity.getAdmin();
                proceduresProcedureEntity.setAdmin(administrator);
                proceduresProcedureEntity = em.merge(proceduresProcedureEntity);
                if (oldAdminOfProceduresProcedureEntity != null) {
                    oldAdminOfProceduresProcedureEntity.getProcedures().remove(proceduresProcedureEntity);
                    oldAdminOfProceduresProcedureEntity = em.merge(oldAdminOfProceduresProcedureEntity);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrator administrator) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrator persistentAdministrator = em.find(Administrator.class, administrator.getId());
            List<ProcedureEntity> proceduresOld = persistentAdministrator.getProcedures();
            List<ProcedureEntity> proceduresNew = administrator.getProcedures();
            List<ProcedureEntity> attachedProceduresNew = new ArrayList<ProcedureEntity>();
            for (ProcedureEntity proceduresNewProcedureEntityToAttach : proceduresNew) {
                proceduresNewProcedureEntityToAttach = em.getReference(proceduresNewProcedureEntityToAttach.getClass(), proceduresNewProcedureEntityToAttach.getId());
                attachedProceduresNew.add(proceduresNewProcedureEntityToAttach);
            }
            proceduresNew = attachedProceduresNew;
            administrator.setProcedures(proceduresNew);
            administrator = em.merge(administrator);
            for (ProcedureEntity proceduresOldProcedureEntity : proceduresOld) {
                if (!proceduresNew.contains(proceduresOldProcedureEntity)) {
                    proceduresOldProcedureEntity.setAdmin(null);
                    proceduresOldProcedureEntity = em.merge(proceduresOldProcedureEntity);
                }
            }
            for (ProcedureEntity proceduresNewProcedureEntity : proceduresNew) {
                if (!proceduresOld.contains(proceduresNewProcedureEntity)) {
                    Administrator oldAdminOfProceduresNewProcedureEntity = proceduresNewProcedureEntity.getAdmin();
                    proceduresNewProcedureEntity.setAdmin(administrator);
                    proceduresNewProcedureEntity = em.merge(proceduresNewProcedureEntity);
                    if (oldAdminOfProceduresNewProcedureEntity != null && !oldAdminOfProceduresNewProcedureEntity.equals(administrator)) {
                        oldAdminOfProceduresNewProcedureEntity.getProcedures().remove(proceduresNewProcedureEntity);
                        oldAdminOfProceduresNewProcedureEntity = em.merge(oldAdminOfProceduresNewProcedureEntity);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = administrator.getId();
                if (findAdministrator(id) == null) {
                    throw new NonexistentEntityException("The administrator with id " + id + " no longer exists.");
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
            Administrator administrator;
            try {
                administrator = em.getReference(Administrator.class, id);
                administrator.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrator with id " + id + " no longer exists.", enfe);
            }
            List<ProcedureEntity> procedures = administrator.getProcedures();
            for (ProcedureEntity proceduresProcedureEntity : procedures) {
                proceduresProcedureEntity.setAdmin(null);
                proceduresProcedureEntity = em.merge(proceduresProcedureEntity);
            }
            em.remove(administrator);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrator> findAdministratorEntities() {
        return findAdministratorEntities(true, -1, -1);
    }

    public List<Administrator> findAdministratorEntities(int maxResults, int firstResult) {
        return findAdministratorEntities(false, maxResults, firstResult);
    }

    private List<Administrator> findAdministratorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrator.class));
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

    public Administrator findAdministrator(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrator.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministratorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrator> rt = cq.from(Administrator.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EntityManagerFactoryProvider;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.ws.rs.BadRequestException;

/**
 *
 * @author mover
 */
public class SchoolDataJpaController implements Serializable {

    protected static final Logger LOG = Logger.getLogger(SchoolDataJpaController.class.getName());

    private static SchoolDataJpaController controller = null;

    private EntityManagerFactory emf = null;

    public static SchoolDataJpaController getInstance() {
        if (controller == null) {
            controller = new SchoolDataJpaController();
        }
        return controller;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public SchoolDataJpaController() {

        this.emf = EntityManagerFactoryProvider.getInstance().getFactory(
                EntityManagerFactoryProvider.DBModule.SC_BACK, "scholar-backoffice");

    }

    public SchoolData create(SchoolData entity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception eml) {
            LOG.log(Level.INFO, eml.toString());
            throw eml;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return entity;

    }

    public void edit(SchoolData schoolData) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            schoolData = em.merge(schoolData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = schoolData.getId().intValue();
                if (findSchoolData(id) == null) {
                    throw new BadRequestException("The Contact with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public SchoolData findSchoolData(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SchoolData.class, id);
        } finally {
            em.close();
        }
    }

    public SchoolData findSchoolDataByName(String name) {
        LOG.log(Level.INFO, "------------------------------ LOOK LOOK  -----------------------{0}------------");

        EntityManager em = getEntityManager();

        List<SchoolData> schoolDatas = null;
        try {

            Query query = em.createNamedQuery("SchoolData.findByName");
            query.setParameter("name", name);
            schoolDatas = query.getResultList();

        } catch (Exception er) {

            throw er;

        } finally {
            em.close();
        }

        if (schoolDatas != null) {
            return schoolDatas.get(0);
        }

        return null;
    }

    private List<SchoolData> findSchoolDataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SchoolData.class));
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

    public List<SchoolData> findSchoolDataEntities() {
        return findSchoolDataEntities(true, -1, -1);
    }

    public List<SchoolData> findSchoolDataEntities(int maxResults, int firstResult) {
        return findSchoolDataEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SchoolData> rt = cq.from(SchoolData.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

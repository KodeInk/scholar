/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Exams;
import com.codemovers.scholar.engine.db.entities.Grading;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author Manny
 */
public class GradingJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(GradingJpaController.class.getName());

    private static GradingJpaController controller = null;

    public static GradingJpaController getInstance() {
        if (controller == null) {
            controller = new GradingJpaController();
        }
        return controller;
    }

    public GradingJpaController() {
        super(Grading.class);
    }

    public List<Grading> findGrading(String name, String code, SchoolData data) {
        List<Grading> gradingList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("Grading.findByNameOrCode");
        query.setParameter("name", name);
        query.setParameter("code", code);
        try {
            gradingList = query.getResultList();

        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return gradingList;
    }

    public List<Grading> findGrading(String name, String code, Integer id, SchoolData data) {
        List<Grading> gradingList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("Grading.findByNameOrCodeOnEdit");
        query.setParameter("name", name);
        query.setParameter("code", code);
        query.setParameter("id", id.longValue());
        try {
            gradingList = query.getResultList();

        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return gradingList;
    }

    public Grading create(Grading entity, SchoolData data) {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
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

    public Grading edit(Grading entity, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entity.getId().intValue();
                if (findGrading(id, data) == null) {
                    throw new BadRequestException("The Contact with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return entity;
    }

    public Grading findGrading(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            return em.find(Grading.class, id.longValue());
        } finally {
            em.close();
        }
    }

    public List<Grading> query(String searchQuery, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Grading> list = new ArrayList<>();
        try {
            Query query = getQuery(em, searchQuery);
            query.setMaxResults(maxResults);
            query.setFirstResult(firstResult);
            list = query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }

        return list;
    }

    public Query getQuery(EntityManager em, String searchQuery) {

        Query query = em.createQuery(""
                + "SELECT GRD FROM Grading GRD "
                + " WHERE GRD.name LIKE :name"
                + " OR GRD.code LIKE :code"
                + "");

        query.setParameter("name", "%" + searchQuery + "%");
        query.setParameter("code", "%" + searchQuery + "%");

        return query;

    }

    private List<Grading> findGradingEntities(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grading.class));
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

    public List<Grading> findGradingEntities(SchoolData data) {
        return findGradingEntities(true, -1, -1, data);
    }

    public List<Grading> findGradingEntities(int maxResults, int firstResult, SchoolData data) {
        return findGradingEntities(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grading> rt = cq.from(Grading.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

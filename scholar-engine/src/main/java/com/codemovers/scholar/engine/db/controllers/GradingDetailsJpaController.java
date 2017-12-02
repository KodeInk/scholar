/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.Exams;
import com.codemovers.scholar.engine.db.entities.GradingDetails;
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
public class GradingDetailsJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(GradingDetailsJpaController.class.getName());

    private static GradingDetailsJpaController controller = null;

    public static GradingDetailsJpaController getInstance() {
        if (controller == null) {
            controller = new GradingDetailsJpaController();
        }
        return controller;
    }

    public GradingDetailsJpaController() {
        super(GradingDetails.class);
    }

    public GradingDetails create(GradingDetails entity) {
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

    public void edit(GradingDetails gradingDetails) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            gradingDetails = em.merge(gradingDetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gradingDetails.getId().intValue();
                if (findGradingDetail(id) == null) {
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

    public GradingDetails findGradingDetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GradingDetails.class, id);
        } finally {
            em.close();
        }
    }

    private List<GradingDetails> findGradingDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exams.class));
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

    public List<GradingDetails> findGradingDetailEntities() {
        return findGradingDetailEntities(true, -1, -1);
    }

    public List<GradingDetails> findGradingDetailEntities(int maxResults, int firstResult) {
        return findGradingDetailEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GradingDetails> rt = cq.from(GradingDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }


}

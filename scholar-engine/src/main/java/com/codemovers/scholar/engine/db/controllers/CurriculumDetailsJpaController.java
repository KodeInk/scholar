/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.Curriculum;
import com.codemovers.scholar.engine.db.entities.CurriculumDetails;
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
 * @author mover
 */
public class CurriculumDetailsJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(CurriculumDetailsJpaController.class.getName());

    private static CurriculumDetailsJpaController controller = null;

    public static CurriculumDetailsJpaController getInstance() {
        if (controller == null) {
            controller = new CurriculumDetailsJpaController();
        }
        return controller;
    }

    public CurriculumDetailsJpaController() {
        super(CurriculumDetails.class);
    }

    public CurriculumDetails create(CurriculumDetails entity) {
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

    public void edit(CurriculumDetails curriculum_detail) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            curriculum_detail = em.merge(curriculum_detail);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = curriculum_detail.getId().intValue();
                if (findCurriculumDetail(id) == null) {
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

    public CurriculumDetails findCurriculumDetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CurriculumDetails.class, id);
        } finally {
            em.close();
        }
    }

    private List<CurriculumDetails> findCurriculumDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CurriculumDetails.class));
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

    public List<CurriculumDetails> findCurriculumDetailEntities() {
        return findCurriculumDetailEntities(true, -1, -1);
    }

    public List<CurriculumDetails> findCurriculumDetailEntities(int maxResults, int firstResult) {
        return findCurriculumDetailEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curriculum> rt = cq.from(Curriculum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

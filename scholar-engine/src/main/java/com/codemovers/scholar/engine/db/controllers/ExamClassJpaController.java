/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.Curriculum;
import com.codemovers.scholar.engine.db.entities.CurriculumDetails;
import com.codemovers.scholar.engine.db.entities.ExamClass;
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
public class ExamClassJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(ExamClassJpaController.class.getName());

    private static ExamClassJpaController controller = null;

    public static ExamClassJpaController getInstance() {
        if (controller == null) {
            controller = new ExamClassJpaController();
        }
        return controller;
    }

    public ExamClassJpaController() {
        super(ExamClass.class);
    }

    public ExamClass create(ExamClass entity) {
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

    public void edit(ExamClass exam_class) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            exam_class = em.merge(exam_class);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exam_class.getId().intValue();
                if (findExamClass(id) == null) {
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

    public ExamClass findExamClass(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamClass.class, id);
        } finally {
            em.close();
        }
    }

    private List<ExamClass> findExamClassEntities(boolean all, int maxResults, int firstResult) {
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

    public List<ExamClass> findExamClassEntities() {
        return findExamClassEntities(true, -1, -1);
    }

    public List<ExamClass> findExamClassEntities(int maxResults, int firstResult) {
        return findExamClassEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExamClass> rt = cq.from(ExamClass.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

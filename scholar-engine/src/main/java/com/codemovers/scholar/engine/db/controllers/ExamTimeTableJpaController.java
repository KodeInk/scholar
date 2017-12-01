/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.ExamTem;
import com.codemovers.scholar.engine.db.entities.ExamTimetable;
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
public class ExamTimeTableJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(ExamTimeTableJpaController.class.getName());

    private static ExamTimeTableJpaController controller = null;

    public static ExamTimeTableJpaController getInstance() {
        if (controller == null) {
            controller = new ExamTimeTableJpaController();
        }
        return controller;
    }

    public ExamTimeTableJpaController() {
        super(ExamTimetable.class);
    }

    public ExamTimetable create(ExamTimetable entity) {
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

    public void edit(ExamTimetable exam_time_table) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            exam_time_table = em.merge(exam_time_table);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exam_time_table.getId().intValue();
                if (findExamTerm(id) == null) {
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

    public ExamTimetable findExamTerm(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamTimetable.class, id);
        } finally {
            em.close();
        }
    }

    private List<ExamTimetable> findExamTimeTableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExamTimetable.class));
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

    public List<ExamTimetable> findExamTimeTableEntities() {
        return findExamTimeTableEntities(true, -1, -1);
    }

    public List<ExamTimetable> findExamTimeTableEntities(int maxResults, int firstResult) {
        return findExamTimeTableEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExamTimetable> rt = cq.from(ExamTem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }


}

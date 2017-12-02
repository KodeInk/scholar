/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.Subjects;
import com.codemovers.scholar.engine.db.entities.TeachingTimetable;
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
public class TeachingTimetableJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(TeachingTimetableJpaController.class.getName());

    private static TeachingTimetableJpaController controller = null;

    public static TeachingTimetableJpaController getInstance() {
        if (controller == null) {
            controller = new TeachingTimetableJpaController();
        }
        return controller;
    }

    public TeachingTimetableJpaController() {
        super(TeachingTimetable.class);
    }

    public TeachingTimetable create(TeachingTimetable entity) {
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

    public void edit(TeachingTimetable teachingTimetable) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            teachingTimetable = em.merge(teachingTimetable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = teachingTimetable.getId().intValue();
                if (findTeachingTimetable(id) == null) {
                    throw new BadRequestException("The Inventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public TeachingTimetable findTeachingTimetable(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(TeachingTimetable.class, id);
        } finally {
            em.close();
        }
    }

    private List<TeachingTimetable> findTeachingTimetables(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TeachingTimetable.class));
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

    public List<TeachingTimetable> findTeachingTimetables() {
        return findTeachingTimetables(true, -1, -1);
    }

    public List<TeachingTimetable> findTeachingTimetables(int maxResults, int firstResult) {
        return findTeachingTimetables(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TeachingTimetable> rt = cq.from(TeachingTimetable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

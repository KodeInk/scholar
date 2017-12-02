/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.SubjectPapers;
import com.codemovers.scholar.engine.db.entities.SubjectTeachers;
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
public class SubjectTeachersJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectTeachersJpaController.class.getName());

    private static SubjectTeachersJpaController controller = null;

    public static SubjectTeachersJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectTeachersJpaController();
        }
        return controller;
    }

    public SubjectTeachersJpaController() {
        super(SubjectTeachers.class);
    }

    public SubjectTeachers create(SubjectTeachers entity) {
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

    public void edit(SubjectTeachers subjectTeachers) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            subjectTeachers = em.merge(subjectTeachers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjectTeachers.getId().intValue();
                if (findSubjectTeacher(id) == null) {
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

    public SubjectTeachers findSubjectTeacher(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(SubjectTeachers.class, id);
        } finally {
            em.close();
        }
    }

    private List<SubjectTeachers> findSubjectTeachers(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubjectTeachers.class));
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

    public List<SubjectTeachers> findSubjectTeachers() {
        return findSubjectTeachers(true, -1, -1);
    }

    public List<SubjectTeachers> findSubjectTeachers(int maxResults, int firstResult) {
        return findSubjectTeachers(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubjectTeachers> rt = cq.from(SubjectTeachers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

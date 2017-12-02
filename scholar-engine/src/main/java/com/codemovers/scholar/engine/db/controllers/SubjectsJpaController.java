/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.SubjectTeachers;
import com.codemovers.scholar.engine.db.entities.Subjects;
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
public class SubjectsJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectsJpaController.class.getName());

    private static SubjectsJpaController controller = null;

    public static SubjectsJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectsJpaController();
        }
        return controller;
    }

    public SubjectsJpaController() {
        super(Subjects.class);
    }

    public Subjects create(Subjects entity) {
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

    public void edit(Subjects subjects) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            subjects = em.merge(subjects);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjects.getId().intValue();
                if (SubjectsJpaController.this.findSubjects(id) == null) {
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

    public Subjects findSubjects(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Subjects.class, id);
        } finally {
            em.close();
        }
    }

    private List<Subjects> findSubjects(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subjects.class));
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

    public List<Subjects> findSubjects() {
        return findSubjects(true, -1, -1);
    }

    public List<Subjects> findSubjects(int maxResults, int firstResult) {
        return findSubjects(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subjects> rt = cq.from(Subjects.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

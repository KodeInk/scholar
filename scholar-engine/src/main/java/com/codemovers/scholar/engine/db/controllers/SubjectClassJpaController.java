/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.StudyYearCurriculum;
import com.codemovers.scholar.engine.db.entities.SubjectClass;
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
public class SubjectClassJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectClassJpaController.class.getName());

    private static SubjectClassJpaController controller = null;

    public static SubjectClassJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectClassJpaController();
        }
        return controller;
    }

    public SubjectClassJpaController() {
        super(SubjectClass.class);
    }

    public SubjectClass create(SubjectClass entity) {
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

    public void edit(SubjectClass subjectClass) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            subjectClass = em.merge(subjectClass);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjectClass.getId().intValue();
                if (findSubjectClass(id) == null) {
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

    public SubjectClass findSubjectClass(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(SubjectClass.class, id);
        } finally {
            em.close();
        }
    }

    private List<SubjectClass> findSubjectClasses(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudyYearCurriculum.class));
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

    public List<SubjectClass> findSubjectClasses() {
        return findSubjectClasses(true, -1, -1);
    }

    public List<SubjectClass> findSubjectClasses(int maxResults, int firstResult) {
        return findSubjectClasses(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubjectClass> rt = cq.from(SubjectClass.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

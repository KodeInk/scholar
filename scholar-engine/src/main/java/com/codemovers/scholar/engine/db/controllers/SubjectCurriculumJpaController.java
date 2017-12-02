/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.StudyYearCurriculum;
import com.codemovers.scholar.engine.db.entities.SubjectClass;
import com.codemovers.scholar.engine.db.entities.SubjectCurriculum;
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
public class SubjectCurriculumJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectCurriculumJpaController.class.getName());

    private static SubjectCurriculumJpaController controller = null;

    public static SubjectCurriculumJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectCurriculumJpaController();
        }
        return controller;
    }

    public SubjectCurriculumJpaController() {
        super(SubjectCurriculum.class);
    }

    public SubjectCurriculum create(SubjectCurriculum entity) {
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

    public void edit(SubjectCurriculum subjectCurriculum) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            subjectCurriculum = em.merge(subjectCurriculum);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjectCurriculum.getId().intValue();
                if (findSubjectCurriculum(id) == null) {
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

    public SubjectCurriculum findSubjectCurriculum(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(SubjectCurriculum.class, id);
        } finally {
            em.close();
        }
    }

    private List<SubjectCurriculum> findSubjectCurriculums(boolean all, int maxResults, int firstResult) {
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

    public List<SubjectCurriculum> findSubjectCurriculums() {
        return findSubjectCurriculums(true, -1, -1);
    }

    public List<SubjectCurriculum> findSubjectCurriculums(int maxResults, int firstResult) {
        return findSubjectCurriculums(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubjectCurriculum> rt = cq.from(SubjectCurriculum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.StudyYearCurriculum;
import com.codemovers.scholar.engine.db.entities.SubjectGrading;
import com.codemovers.scholar.engine.db.entities.SubjectPapers;
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
public class SubjectPapersJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectPapersJpaController.class.getName());

    private static SubjectPapersJpaController controller = null;

    public static SubjectPapersJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectPapersJpaController();
        }
        return controller;
    }

    public SubjectPapersJpaController() {
        super(SubjectPapers.class);
    }

    public SubjectPapers create(SubjectPapers entity) {
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

    public void edit(SubjectPapers subjectPapers) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            subjectPapers = em.merge(subjectPapers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjectPapers.getId().intValue();
                if (SubjectPapersJpaController.this.findSubjectPaper(id) == null) {
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

    public SubjectPapers findSubjectPaper(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(SubjectPapers.class, id);
        } finally {
            em.close();
        }
    }

    private List<SubjectPapers> findSubjectPapers(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubjectPapers.class));
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

    public List<SubjectPapers> findSubjectPapers() {
        return findSubjectPapers(true, -1, -1);
    }

    public List<SubjectPapers> findSubjectPapers(int maxResults, int firstResult) {
        return findSubjectPapers(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubjectPapers> rt = cq.from(SubjectPapers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

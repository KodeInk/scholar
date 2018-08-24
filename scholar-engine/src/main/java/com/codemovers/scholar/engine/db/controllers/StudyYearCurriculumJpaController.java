/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudyYearCurriculum;
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
 * @author mover 8/24/2018
 */
public class StudyYearCurriculumJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudyYearCurriculumJpaController.class.getName());

    private static StudyYearCurriculumJpaController controller = null;

    public static StudyYearCurriculumJpaController getInstance() {
        if (controller == null) {
            controller = new StudyYearCurriculumJpaController();
        }
        return controller;
    }

    public StudyYearCurriculumJpaController() {
        super(StudyYearCurriculum.class);
    }

    public StudyYearCurriculum create(StudyYearCurriculum entity, SchoolData data) {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
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

    public void edit(StudyYearCurriculum studyYearCurriculum, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studyYearCurriculum = em.merge(studyYearCurriculum);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studyYearCurriculum.getId().intValue();
                if (findStudyYearCurriculum(id, data) == null) {
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

    public StudyYearCurriculum findStudyYearCurriculum(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(StudyYearCurriculum.class, id);
        } finally {
            em.close();
        }
    }

    private List<StudyYearCurriculum> findStudyYearCurriculums(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
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

    public List<StudyYearCurriculum> findStudyYearCurriculums(SchoolData data) {
        return findStudyYearCurriculums(true, -1, -1, data);
    }

    public List<StudyYearCurriculum> findStudyYearCurriculums(int maxResults, int firstResult, SchoolData data) {
        return findStudyYearCurriculums(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudyYearCurriculum> rt = cq.from(StudyYearCurriculum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

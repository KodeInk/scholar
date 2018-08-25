/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.StudyYear;
import java.util.ArrayList;
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
public class StudyYearJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudyYearJpaController.class.getName());

    private static StudyYearJpaController controller = null;

    public static StudyYearJpaController getInstance() {
        if (controller == null) {
            controller = new StudyYearJpaController();
        }
        return controller;
    }

    public StudyYearJpaController() {
        super(StudyYear.class);
    }

    public StudyYear create(StudyYear entity, SchoolData data) {
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

    public StudyYear edit(StudyYear studyYear, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studyYear = em.merge(studyYear);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studyYear.getId().intValue();
                if (findStudyYear(id, data) == null) {
                    throw new BadRequestException("The Inventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return studyYear;
    }

    public StudyYear findStudyYear(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(StudyYear.class, id.longValue());
        } catch (Exception er) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<StudyYear> query(String searchQuery, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<StudyYear> list = new ArrayList<>();
        try {
            Query query = getQuery(em, searchQuery);
            query.setMaxResults(maxResults);
            query.setFirstResult(firstResult);
            list = query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }

        return list;
    }

    private List<StudyYear> findStudyYears(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudyYear.class));
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

    public List<StudyYear> findStudyYears(SchoolData data) {
        return findStudyYears(true, -1, -1, data);
    }

    public List<StudyYear> findStudyYears(int maxResults, int firstResult, SchoolData data) {
        return findStudyYears(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudyYear> rt = cq.from(StudyYear.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Query getQuery(EntityManager em, String searchQuery) {

        Query query = em.createQuery(""
                + "select ST FROM StudyYear ST "
                + " WHERE ST.theme LIKE :theme"
                + " OR ST.code LIKE :code"
                + "");

        query.setParameter("theme", "%" + searchQuery + "%");

        return query;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudyYearCurriculum;
import com.codemovers.scholar.engine.db.entities.SubjectGrading;
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
public class SubjectGradingJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectGradingJpaController.class.getName());

    private static SubjectGradingJpaController controller = null;

    public static SubjectGradingJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectGradingJpaController();
        }
        return controller;
    }

    public SubjectGradingJpaController() {
        super(SubjectGrading.class);
    }

    public SubjectGrading create(SubjectGrading entity, SchoolData data) {
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

    public void deleteSubjectByGradingId(Integer gradingId, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            Query query = em.createQuery(""
                    + "SELECT SUBGD FROM SubjectGrading SUBGD "
                    + " WHERE SUBGD.grading.id = :id"
                    + "");
            query.setParameter("id", gradingId.longValue());
            List<SubjectGrading> subjectGrading = query.getResultList();

            subjectGrading.stream().map((curriculum) -> {
                em.getTransaction().begin();
                em.remove(curriculum);
                return curriculum;
            }).forEachOrdered((_item) -> {
                em.getTransaction().commit();
            });

        }catch(Exception er){
            er.printStackTrace();
            throw er;
        } 
        finally {
            em.close();
        }
    }

    public void edit(SubjectGrading subjectGrading, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            subjectGrading = em.merge(subjectGrading);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjectGrading.getId().intValue();
                if (findSubjectGrading(id, data) == null) {
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

    public SubjectGrading findSubjectGrading(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(SubjectGrading.class, id);
        } finally {
            em.close();
        }
    }

    private List<SubjectGrading> findSubjectGradings(boolean all, int maxResults, int firstResult, SchoolData data) {
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

    public List<SubjectGrading> findSubjectGradings(SchoolData data) {
        return findSubjectGradings(true, -1, -1, data);
    }

    public List<SubjectGrading> findSubjectGradings(int maxResults, int firstResult, SchoolData data) {
        return findSubjectGradings(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubjectGrading> rt = cq.from(SubjectGrading.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

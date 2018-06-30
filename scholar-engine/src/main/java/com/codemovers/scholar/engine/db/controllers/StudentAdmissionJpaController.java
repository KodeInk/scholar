/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.helper.Utilities;
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
 * @author mover 6/15/2018
 */
public class StudentAdmissionJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudentAdmissionJpaController.class.getName());

    private static StudentAdmissionJpaController controller = null;

    public StudentAdmissionJpaController() {
        super(StudentAdmission.class);
    }

    /**
     *
     * @return
     */
    public static StudentAdmissionJpaController getInstance() {
        if (controller == null) {
            controller = new StudentAdmissionJpaController();
        }
        return controller;
    }

    /**
     *
     * @param entity
     * @param data
     * @return
     */
    public StudentAdmission create(StudentAdmission entity, SchoolData data) {
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

    /**
     *
     * @param studentAdmission
     * @param data
     * @return
     * @throws Exception
     */
    public StudentAdmission edit(StudentAdmission studentAdmission, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studentAdmission = em.merge(studentAdmission);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentAdmission.getId().intValue();
                if (findStudentAdmission(id, data) == null) {
                    throw new BadRequestException("The Inventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return studentAdmission;
    }

    /**
     *
     * @param id
     * @param data
     * @return
     */
    public StudentAdmission findStudentAdmission(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(StudentAdmission.class, id.longValue());
        } catch (Exception er) {
            er.printStackTrace();
            LOG.log(Level.SEVERE, er.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param admissiion_no
     * @param data
     * @return
     */
    public List<StudentAdmission> findStudentAdmission(String admissiion_no, SchoolData data) {
        List<StudentAdmission> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            Query query = em.createNamedQuery("StudentAdmission.findByAdmissionNo");
            query.setParameter("admissionNo", admissiion_no);
            termsList = query.getResultList();
            LOG.log(Level.FINE, " Student Admission found  with admission number {0}", new Object[]{admissiion_no});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }

        return termsList;

    }

    private List<StudentAdmission> findStudentAdmissions(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {

            Query q = getQuery(em);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @param studyYear
     * @param data
     * @return
     */
    public List<StudentAdmission> findStudentAdmissions(int maxResults, int firstResult, Integer studyYear, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {

            Query query = getQuery(em, studyYear);

            query.setMaxResults(maxResults);
            query.setFirstResult(firstResult);

            return query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @param studyYear
     * @param admissionClass
     * @param data
     * @return
     */
    public List<StudentAdmission> findStudentAdmissions(int maxResults, int firstResult, Integer studyYear, Integer admissionClass, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {

            Query query = getQuery(em,studyYear, admissionClass);

            query.setMaxResults(maxResults);
            query.setFirstResult(firstResult);

            return query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param data
     * @return
     */
    public List<StudentAdmission> findStudentAdmissions(SchoolData data) {
        return findStudentAdmissions(true, -1, -1, data);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @param data
     * @return
     */
    public List<StudentAdmission> findStudentAdmissions(int maxResults, int firstResult, SchoolData data) {
        return findStudentAdmissions(false, maxResults, firstResult, data);
    }

    /**
     *
     * @param data
     * @return
     */
    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentAdmission> rt = cq.from(StudentAdmission.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param em
     * @return
     */
    public Query getQuery(EntityManager em) {
        Query q = em.createQuery(""
                + "SELECT SA FROM StudentAdmission SA "
                + " LEFT  JOIN FETCH SA.student "
                + "");

        return q;
    }

    /**
     *
     * @param em
     * @param studyYear
     * @return
     */
    public Query getQuery(EntityManager em, Integer studyYear) {
        Query q = em.createQuery(""
                + "SELECT SA FROM StudentAdmission SA "
                + " LEFT  JOIN FETCH SA.student "
                + " LEFT  JOIN FETCH SA.admissionTerm "
                + " WHERE "
                + "SA.admissionTerm.studyYear.id =  :studyyear"
                + "");

        q.setParameter("studyyear", studyYear.longValue());

        return q;
    }

    /**
     *
     * @param em
     * @param studyYear
     * @param admissionClass
     * @return
     */
    public Query getQuery(EntityManager em, Integer studyYear, Integer admissionClass) {
        Query q = em.createQuery(""
                + "SELECT SA FROM StudentAdmission SA "
                + " LEFT  JOIN FETCH SA.student "
                + " LEFT  JOIN FETCH SA.admissionTerm "
                + " WHERE "
                + "SA.admissionTerm.studyYear.id =  :studyyear"
                + " AND SA.admissionClass.id =  :admissionclass"
                + "");

        q.setParameter("studyyear", studyYear.longValue());
        q.setParameter("admissionclass", admissionClass.longValue());

        return q;
    }

}

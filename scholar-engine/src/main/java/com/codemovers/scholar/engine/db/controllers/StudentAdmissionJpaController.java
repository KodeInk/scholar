/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
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
public class StudentAdmissionJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudentAdmissionJpaController.class.getName());

    private static StudentAdmissionJpaController controller = null;

    public static StudentAdmissionJpaController getInstance() {
        if (controller == null) {
            controller = new StudentAdmissionJpaController();
        }
        return controller;
    }

    public StudentAdmissionJpaController() {
        super(StudentAdmission.class);
    }

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

    public void edit(StudentAdmission studentAdmission, SchoolData data) throws Exception {
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
    }

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

    private List<StudentAdmission> findStudentAdmissions(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentAdmission.class));
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

    public List<StudentAdmission> findStudentAdmissions(SchoolData data) {
        return findStudentAdmissions(true, -1, -1, data);
    }

    public List<StudentAdmission> findStudentAdmissions(int maxResults, int firstResult, SchoolData data) {
        return findStudentAdmissions(false, maxResults, firstResult, data);
    }

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

}

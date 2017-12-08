/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentSubjectRegistration;
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
public class StudentSubjectRegistrationJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudentSubjectRegistrationJpaController.class.getName());

    private static StudentSubjectRegistrationJpaController controller = null;

    public static StudentSubjectRegistrationJpaController getInstance() {
        if (controller == null) {
            controller = new StudentSubjectRegistrationJpaController();
        }
        return controller;
    }

    public StudentSubjectRegistrationJpaController() {
        super(StudentSubjectRegistration.class);
    }

    public StudentSubjectRegistration create(StudentSubjectRegistration entity, SchoolData data) {
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

    public void edit(StudentSubjectRegistration studentSubjectRegistration, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studentSubjectRegistration = em.merge(studentSubjectRegistration);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentSubjectRegistration.getId().intValue();
                if (findStudentSubjectRegistration(id,data) == null) {
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

    public StudentSubjectRegistration findStudentSubjectRegistration(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(StudentSubjectRegistration.class, id);
        } finally {
            em.close();
        }
    }

    private List<StudentSubjectRegistration> findStudentSubjectRegistrations(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentSubjectRegistration.class));
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

    public List<StudentSubjectRegistration> findStudentSubjectRegistrations(SchoolData data) {
        return findStudentSubjectRegistrations(true, -1, -1, data);
    }

    public List<StudentSubjectRegistration> findStudentSubjectRegistrations(int maxResults, int firstResult, SchoolData data) {
        return findStudentSubjectRegistrations(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentSubjectRegistration> rt = cq.from(StudentSubjectRegistration.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.StudentExamRegistration;
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
public class StudentExamRegistrationJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudentExamRegistrationJpaController.class.getName());

    private static StudentExamRegistrationJpaController controller = null;

    public static StudentExamRegistrationJpaController getInstance() {
        if (controller == null) {
            controller = new StudentExamRegistrationJpaController();
        }
        return controller;
    }

    public StudentExamRegistrationJpaController() {
        super(StudentExamRegistration.class);
    }

    public StudentExamRegistration create(StudentExamRegistration entity, SchoolData data) {
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

    public void edit(StudentExamRegistration studentExamRegistration, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studentExamRegistration = em.merge(studentExamRegistration);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentExamRegistration.getId().intValue();
                if (findStudentExamRegistration(id,data) == null) {
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

    public StudentExamRegistration findStudentExamRegistration(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(StudentExamRegistration.class, id);
        } finally {
            em.close();
        }
    }

    private List<StudentExamRegistration> findStudentExamRegistrations(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentExamRegistration.class));
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

    public List<StudentExamRegistration> findStudentExamRegistrations(SchoolData data) {
        return findStudentExamRegistrations(true, -1, -1, data);
    }

    public List<StudentExamRegistration> findStudentExamRegistrations(int maxResults, int firstResult, SchoolData data) {
        return findStudentExamRegistrations(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentExamRegistration> rt = cq.from(StudentExamRegistration.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

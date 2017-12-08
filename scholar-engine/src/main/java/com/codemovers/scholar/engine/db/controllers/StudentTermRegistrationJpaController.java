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
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
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
public class StudentTermRegistrationJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudentTermRegistrationJpaController.class.getName());

    private static StudentTermRegistrationJpaController controller = null;

    public static StudentTermRegistrationJpaController getInstance() {
        if (controller == null) {
            controller = new StudentTermRegistrationJpaController();
        }
        return controller;
    }

    public StudentTermRegistrationJpaController() {
        super(StudentTermRegistration.class);
    }

    public StudentTermRegistration create(StudentTermRegistration entity, SchoolData data) {
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

    public void edit(StudentTermRegistration studentTermRegistration, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studentTermRegistration = em.merge(studentTermRegistration);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentTermRegistration.getId().intValue();
                if (findStudentTermRegistration(id, data) == null) {
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

    public StudentTermRegistration findStudentTermRegistration(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(StudentTermRegistration.class, id);
        } finally {
            em.close();
        }
    }

    private List<StudentTermRegistration> findStudentTermRegistrations(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentTermRegistration.class));
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

    public List<StudentTermRegistration> findStudentTermRegistrations(SchoolData data) {
        return findStudentTermRegistrations(true, -1, -1, data);
    }

    public List<StudentTermRegistration> findStudentTermRegistrations(int maxResults, int firstResult, SchoolData data) {
        return findStudentTermRegistrations(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentTermRegistration> rt = cq.from(StudentTermRegistration.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

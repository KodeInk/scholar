/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
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
public class StudentAdmissionJpaController extends JpaController {

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

    public StudentAdmission create(StudentAdmission entity) {
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

    public void edit(StudentAdmission studentAdmission) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            studentAdmission = em.merge(studentAdmission);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentAdmission.getId().intValue();
                if (findStudentAdmission(id) == null) {
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

    public StudentAdmission findStudentAdmission(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(StudentAdmission.class, id);
        } finally {
            em.close();
        }
    }

    private List<StudentAdmission> findStudentAdmissions(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
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

    public List<StudentAdmission> findStudentAdmissions() {
        return findStudentAdmissions(true, -1, -1);
    }

    public List<StudentAdmission> findStudentAdmissions(int maxResults, int firstResult) {
        return findStudentAdmissions(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
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

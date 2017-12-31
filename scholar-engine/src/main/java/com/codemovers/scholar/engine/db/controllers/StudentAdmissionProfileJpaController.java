/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.StudentAdmissionProfile;
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
 * @author mover 12/31/2017
 */
public class StudentAdmissionProfileJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudentAdmissionProfileJpaController.class.getName());

    private static StudentAdmissionProfileJpaController controller = null;

    public static StudentAdmissionProfileJpaController getInstance() {
        if (controller == null) {
            controller = new StudentAdmissionProfileJpaController();
        }
        return controller;
    }

    public StudentAdmissionProfileJpaController() {
        super(StudentAdmissionProfile.class);
    }

    public StudentAdmissionProfile create(StudentAdmissionProfile entity, SchoolData data) {
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

    public StudentAdmissionProfile edit(StudentAdmissionProfile studentAdmissionProfile, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studentAdmissionProfile = em.merge(studentAdmissionProfile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentAdmissionProfile.getId().intValue();
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

        return studentAdmissionProfile;
    }

    public StudentAdmissionProfile findStudentAdmission(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(StudentAdmissionProfile.class, id);
        } finally {
            em.close();
        }
    }

    private List<StudentAdmissionProfile> findStudentAdmissionProfiles(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentAdmissionProfile.class));
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

    public List<StudentAdmissionProfile> findStudentAdmissionProfiles(SchoolData data) {
        return findStudentAdmissionProfiles(true, -1, -1, data);
    }

    public List<StudentAdmissionProfile> findStudentAdmissionProfiles(int maxResults, int firstResult, SchoolData data) {
        return findStudentAdmissionProfiles(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentAdmission> rt = cq.from(StudentAdmissionProfile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.Departments;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author mover 3/15/2018
 */
public class DepartmentsJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(DepartmentsJpaController.class.getName());
    private static DepartmentsJpaController controller = null;

    public static DepartmentsJpaController getInstance() {
        if (controller == null) {
            controller = new DepartmentsJpaController();
        }
        return controller;
    }

    public DepartmentsJpaController() {
        super(Departments.class);
    }

    public Departments create(Departments entity, SchoolData data) {
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

    public void edit(Departments department, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            department = em.merge(department);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = department.getId().intValue();
                if (findDepartment(id, data) == null) {
                    throw new BadRequestException("The Department  with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Departments findDepartment(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            return em.find(Departments.class, id.longValue());
        } finally {
            em.close();
        }
    }

    private List<Departments> findDepartmentsEntities(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departments.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception er) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Departments> findDepartmentsEntities(SchoolData data) {
        return findDepartmentsEntities(true, -1, -1, data);
    }

    public List<Departments> findDepartmentsEntities(int maxResults, int firstResult, SchoolData data) {
        return findDepartmentsEntities(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departments> rt = cq.from(Departments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.SubjectTeachers;
import com.codemovers.scholar.engine.db.entities.Subjects;
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
public class SubjectsJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectsJpaController.class.getName());

    private static SubjectsJpaController controller = null;

    public static SubjectsJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectsJpaController();
        }
        return controller;
    }

    public SubjectsJpaController() {
        super(Subjects.class);
    }

    public Subjects create(Subjects entity, SchoolData data) {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            entity = findSubjects(entity.getId().intValue(), data);
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

    public List<Subjects> findSubjects(String name, String code, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Subjects> list = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Subjects.findSubjectByNameORCode");
            query.setParameter("name", name);
            query.setParameter("code", code);

            list = query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }

        return list;
    }
    
     public List<Subjects> findSubjects(String name, String code, Integer id,SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Subjects> list = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Subjects.findSubjectByNameORCodeOnEdit");
            query.setParameter("name", name);
            query.setParameter("code", code);
            query.setParameter("id", id.longValue());

            list = query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }

        return list;
    }
     

    public Subjects edit(Subjects subjects, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            subjects = em.merge(subjects);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjects.getId().intValue();
                if (SubjectsJpaController.this.findSubjects(id, data) == null) {
                    throw new BadRequestException("The Inventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return subjects;
    }

    public Subjects findSubjects(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(Subjects.class, id.longValue());
        } finally {
            em.close();
        }
    }

    private List<Subjects> findSubjects(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subjects.class));
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

    public List<Subjects> findSubjects(SchoolData data) {
        return SubjectsJpaController.this.findSubjects(true, -1, -1, data);
    }

    public List<Subjects> findSubjects(int maxResults, int firstResult, SchoolData data) {
        return SubjectsJpaController.this.findSubjects(false, maxResults, firstResult, data);
    }

    public List<Subjects> query(String searchQuery, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Subjects> list = new ArrayList<>();
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

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subjects> rt = cq.from(Subjects.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Query getQuery(EntityManager em, String searchQuery) {

        Query query = em.createQuery(""
                + "select SUB FROM Subjects SUB "
                + " WHERE SUB.name LIKE :name"
                + " OR SUB.code LIKE :code"
                + " OR SUB.category LIKE :category"
                + "");

        query.setParameter("name", "%" + searchQuery + "%");
        query.setParameter("code", "%" + searchQuery + "%");
        query.setParameter("category", "%" + searchQuery + "%");

        return query;

    }

}

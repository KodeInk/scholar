/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.BookType;
import com.codemovers.scholar.engine.db.entities.Contacts;
import com.codemovers.scholar.engine.db.entities.Curriculum;
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
 * @author mover
 */
public class CurriculumJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(ContactsJpaController.class.getName());

    private static CurriculumJpaController controller = null;

    public static CurriculumJpaController getInstance() {
        if (controller == null) {
            controller = new CurriculumJpaController();
        }
        return controller;
    }

    public CurriculumJpaController() {
        super(Curriculum.class);
    }

    public Curriculum create(Curriculum entity) {
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

    public void edit(Curriculum curriculum) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            curriculum = em.merge(curriculum);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = curriculum.getId().intValue();
                if (findCurriculum(id) == null) {
                    throw new BadRequestException("The Contact with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Curriculum findCurriculum(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curriculum.class, id);
        } finally {
            em.close();
        }
    }

    private List<Curriculum> findCurriculumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BookType.class));
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

    public List<Curriculum> findCurriculumEntities() {
        return findCurriculumEntities(true, -1, -1);
    }

    public List<Curriculum> findCurriculumEntities(int maxResults, int firstResult) {
        return findCurriculumEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curriculum> rt = cq.from(Curriculum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}

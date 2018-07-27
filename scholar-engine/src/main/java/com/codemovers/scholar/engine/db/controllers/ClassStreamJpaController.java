/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.BookType;
import com.codemovers.scholar.engine.db.entities.ClassStream;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author mover
 */
public class ClassStreamJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(ClassStreamJpaController.class.getName());

    private static ClassStreamJpaController controller = null;

    public static ClassStreamJpaController getInstance() {
        if (controller == null) {
            controller = new ClassStreamJpaController();
        }
        return controller;
    }

    public ClassStreamJpaController() {
        super(ClassStream.class);
    }

    public ClassStream create(ClassStream entity, SchoolData data) {
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

    public void edit(ClassStream class_stream, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            class_stream = em.merge(class_stream);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = class_stream.getId().intValue();
                if (findClassStream(id, data) == null) {
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

    public ClassStream findClassStream(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            return em.find(ClassStream.class, id);
        } finally {
            em.close();
        }
    }

    private List<ClassStream> findClassStreamEntities(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
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

    public List<ClassStream> findClassStreamEntities(SchoolData data) {
        return findClassStreamEntities(true, -1, -1, data);
    }

    public List<ClassStream> findClassStreamEntities(int maxResults, int firstResult, SchoolData data) {
        return findClassStreamEntities(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassStream> rt = cq.from(ClassStream.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    
      public void destroy(Integer id, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            ClassStream classStream;
            try {
                classStream = em.getReference(ClassStream.class, id.longValue());
                classStream.getId();
            } catch (EntityNotFoundException enfe) {
                throw enfe;
            }
            em.remove(classStream);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

      
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Roles;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
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
 * @author Manny
 */
public class StreamsJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StreamsJpaController.class.getName());

    private static StreamsJpaController controller = null;

    public static StreamsJpaController getInstance() {
        if (controller == null) {
            controller = new StreamsJpaController();
        }
        return controller;
    }

    public StreamsJpaController() {
        super(Streams.class);
    }

    public Streams create(Streams entity, SchoolData data) {
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

    public Streams edit(Streams stream, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            stream = em.merge(stream);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stream.getId().intValue();

                if (findStream(id, data) == null) {
                    throw new BadRequestException("The Stream with id " + id + " no longer exists.");

                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return stream;
    }

    public Streams findStream(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(Streams.class, id.longValue());
        } finally {
            em.close();
        }
    }

    private List<Streams> findStreams(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Streams.class));
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

    public List<Streams> findStreams(SchoolData data) {
        return findStreams(true, -1, -1, data);
    }

    public List<Streams> findStreams(int maxResults, int firstResult, SchoolData data) {
        return findStreams(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Streams> rt = cq.from(Streams.class);
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
            Streams _stream;
            try {
                _stream = em.getReference(Streams.class, id.longValue());
                _stream.getId();
            } catch (EntityNotFoundException enfe) {
                throw enfe;
            }
            em.remove(_stream);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}

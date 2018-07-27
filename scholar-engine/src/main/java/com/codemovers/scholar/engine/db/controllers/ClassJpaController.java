/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.api.v1.classes.entities.SchoolClass;
import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.BookType;
import com.codemovers.scholar.engine.db.entities.ClassStream;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.ws.rs.BadRequestException;
import org.hibernate.jpa.criteria.OrderImpl;

/**
 *
 * @author mover
 */
public class ClassJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(ClassJpaController.class.getName());

    private static ClassJpaController controller = null;

    /**
     *
     * @return
     */
    public static ClassJpaController getInstance() {
        if (controller == null) {
            controller = new ClassJpaController();
        }
        return controller;
    }

    /**
     *
     */
    public ClassJpaController() {
        super(Classes.class);
    }

    /**
     *
     * @param entity
     * @param data
     * @return
     */
    public Classes create(Classes entity, SchoolData data) {
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

    /**
     *
     * @param _classes
     * @param data
     * @return
     * @throws Exception
     */
    public Classes edit(Classes _classes, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            _classes = em.merge(_classes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = _classes.getId().intValue();

                if (findClass(id, data) == null) {

                    throw new BadRequestException("The Contact with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return _classes;

    }

    /**
     *
     * @param id
     * @param data
     * @return
     */
    public Classes findClass(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            Classes classes = em.find(Classes.class, id.longValue());
            classes.getClassStreamCollection();
            return classes;
        } catch (Exception er) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Classes> query(String searchQuery, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Classes> list = new ArrayList<>();
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

    public List<Classes> findClasses(String name, String code, Long ranking, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Classes> list = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Classes.findClassByNameRankCode");
            query.setParameter("name", name);
            query.setParameter("rank", ranking);
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

    public List<Classes> findClasses(Integer classId, String name, String code, Long ranking, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Classes> list = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Classes.findClassByNameRankCodeOnEdit");
            query.setParameter("name", name);
            query.setParameter("rank", ranking);
            query.setParameter("code", code);
            query.setParameter("id", classId.longValue());

            list = query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }

        return list;
    }

    private List<Classes> findClassEntities(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Classes.class));
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

    /**
     *
     * @param data
     * @return
     */
    public List<Classes> findClassEntities(SchoolData data) {
        return findClassEntities(true, -1, -1, data);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @param data
     * @return
     */
    public List<Classes> findClassEntities(int maxResults, int firstResult, SchoolData data) {
        return findClassEntities(false, firstResult, maxResults, data);
    }

    /**
     *
     * @param data
     * @return
     */
    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Classes> rt = cq.from(Classes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param id
     * @param data
     * @throws Exception
     */
    public void destroy(Integer id, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            Classes _class;
            try {
                _class = em.getReference(Classes.class, id.longValue());
                _class.getId();
            } catch (EntityNotFoundException enfe) {
                throw enfe;
            }
            em.remove(_class);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Query getQuery(EntityManager em, String searchQuery) {

        Query query = em.createQuery(""
                + "select ST FROM Classes ST "
                + " WHERE ST.name LIKE :name"
                + " OR ST.code LIKE :code"
                + "");

        query.setParameter("name", "%" + searchQuery + "%");
        query.setParameter("code", "%" + searchQuery + "%");

        return query;

    }

}

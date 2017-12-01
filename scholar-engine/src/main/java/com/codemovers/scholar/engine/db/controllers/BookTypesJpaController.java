/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.BookType;
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
public class BookTypesJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(BookTypesJpaController.class.getName());

    private static BookTypesJpaController controller = null;

    public static BookTypesJpaController getInstance() {
        if (controller == null) {
            controller = new BookTypesJpaController();
        }
        return controller;
    }

    public BookTypesJpaController() {
        super(BookType.class);
    }

    public BookType create(BookType entity) {
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

    public void edit(BookType book_type) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            book_type = em.merge(book_type);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = book_type.getId().intValue();
                if (findBookType(id) == null) {
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

    public BookType findBookType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BookType.class, id);
        } finally {
            em.close();
        }
    }

    private List<BookType> findBookTypeEntities(boolean all, int maxResults, int firstResult) {
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

    public List<BookType> findBookTypeEntities() {
        return findBookTypeEntities(true, -1, -1);
    }

    public List<BookType> findBookTypeEntities(int maxResults, int firstResult) {
        return findBookTypeEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BookType> rt = cq.from(BookType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

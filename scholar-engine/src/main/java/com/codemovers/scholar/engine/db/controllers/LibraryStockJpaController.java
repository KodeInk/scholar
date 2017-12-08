/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.LibrarySection;
import com.codemovers.scholar.engine.db.entities.LibraryStock;
import com.codemovers.scholar.engine.db.entities.SchoolData;
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
public class LibraryStockJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(LibraryStockJpaController.class.getName());

    private static LibraryStockJpaController controller = null;

    public static LibraryStockJpaController getInstance() {
        if (controller == null) {
            controller = new LibraryStockJpaController();
        }
        return controller;
    }

    public LibraryStockJpaController() {
        super(LibraryStock.class);
    }

    public LibraryStock create(LibraryStock entity, SchoolData data) {
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

    public void edit(LibraryStock librarystock, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            librarystock = em.merge(librarystock);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = librarystock.getId().intValue();
                if (findLibraryStock(id,data) == null) {
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

    public LibraryStock findLibraryStock(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            return em.find(LibraryStock.class, id);
        } finally {
            em.close();
        }
    }

    private List<LibraryStock> findLibraryStocklEntities(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LibraryStock.class));
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

    public List<LibraryStock> findLibraryStocklEntities(SchoolData data) {
        return findLibraryStocklEntities(true, -1, -1, data);
    }

    public List<LibraryStock> findLibraryStocklEntities(int maxResults, int firstResult, SchoolData data) {
        return findLibraryStocklEntities(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LibraryStock> rt = cq.from(LibraryStock.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

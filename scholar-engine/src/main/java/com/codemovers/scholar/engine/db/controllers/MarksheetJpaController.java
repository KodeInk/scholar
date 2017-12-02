/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.LibraryStockInventory;
import com.codemovers.scholar.engine.db.entities.LibraryTransactions;
import com.codemovers.scholar.engine.db.entities.Marksheet;
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
public class MarksheetJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(MarksheetJpaController.class.getName());

    private static MarksheetJpaController controller = null;

    public static MarksheetJpaController getInstance() {
        if (controller == null) {
            controller = new MarksheetJpaController();
        }
        return controller;
    }

    public MarksheetJpaController() {
        super(Marksheet.class);
    }

    public Marksheet create(Marksheet entity) {
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

    public void edit(Marksheet marksheet) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            marksheet = em.merge(marksheet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marksheet.getId().intValue();
                if (findMarkSheet(id) == null) {
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

    public Marksheet findMarkSheet(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Marksheet.class, id);
        } finally {
            em.close();
        }
    }

    private List<Marksheet> findMarkSheets(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marksheet.class));
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

    public List<Marksheet> findMarkSheets() {
        return findMarkSheets(true, -1, -1);
    }

    public List<Marksheet> findMarkSheets(int maxResults, int firstResult) {
        return findMarkSheets(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marksheet> rt = cq.from(Marksheet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

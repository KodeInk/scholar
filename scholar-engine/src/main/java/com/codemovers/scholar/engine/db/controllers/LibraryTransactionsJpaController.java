/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.LibraryStockInventory;
import com.codemovers.scholar.engine.db.entities.LibraryTransactions;
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
public class LibraryTransactionsJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(LibraryTransactionsJpaController.class.getName());

    private static LibraryTransactionsJpaController controller = null;

    public static LibraryTransactionsJpaController getInstance() {
        if (controller == null) {
            controller = new LibraryTransactionsJpaController();
        }
        return controller;
    }

    public LibraryTransactionsJpaController() {
        super(LibraryTransactions.class);
    }

    public LibraryTransactions create(LibraryTransactions entity) {
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

    public void edit(LibraryTransactions transactions) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transactions = em.merge(transactions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactions.getId().intValue();
                if (findTransaction(id) == null) {
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

    public LibraryTransactions findTransaction(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(LibraryTransactions.class, id);
        } finally {
            em.close();
        }
    }

    private List<LibraryTransactions> findTransactions(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq
                    .select(cq.from(LibraryStockInventory.class
                    ));
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

    public List<LibraryTransactions> findTransactions() {
        return findTransactions(true, -1, -1);
    }

    public List<LibraryTransactions> findTransactions(int maxResults, int firstResult) {
        return findTransactions(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LibraryTransactions> rt = cq.from(LibraryTransactions.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

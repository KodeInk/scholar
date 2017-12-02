/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.LibraryStockInventory;
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
public class LibraryStockInventoryJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(LibraryStockJpaController.class.getName());

    private static LibraryStockInventoryJpaController controller = null;

    public static LibraryStockInventoryJpaController getInstance() {
        if (controller == null) {
            controller = new LibraryStockInventoryJpaController();
        }
        return controller;
    }

    public LibraryStockInventoryJpaController() {
        super(LibraryStockInventory.class
        );
    }

    public LibraryStockInventory create(LibraryStockInventory entity) {
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

    public void edit(LibraryStockInventory librarystockinventory) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            librarystockinventory = em.merge(librarystockinventory);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = librarystockinventory.getId().intValue();
                if (findLibraryStockInventory(id) == null) {
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

    public LibraryStockInventory findLibraryStockInventory(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(LibraryStockInventory.class, id);
        } finally {
            em.close();
        }
    }

    private List<LibraryStockInventory> findLibraryStockInventoryEntities(boolean all, int maxResults, int firstResult) {
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

    public List<LibraryStockInventory> findLibraryStockInventoryEntities() {
        return findLibraryStockInventoryEntities(true, -1, -1);
    }

    public List<LibraryStockInventory> findLibraryStockInventoryEntities(int maxResults, int firstResult) {
        return findLibraryStockInventoryEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LibraryStockInventory> rt = cq.from(LibraryStockInventory.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

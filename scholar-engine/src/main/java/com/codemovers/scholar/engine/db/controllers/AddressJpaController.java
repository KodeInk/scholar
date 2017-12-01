/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.JpaController;
import com.codemovers.scholar.engine.db.entities.Addresses;
import com.codemovers.scholar.engine.db.entities.Contacts;
import com.codemovers.scholar.engine.helper.Utilities;
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
 * @author mover
 */
public class AddressJpaController extends JpaController {

    protected static final Logger LOG = Logger.getLogger(AddressJpaController.class.getName());

    private static AddressJpaController controller = null;

    public static AddressJpaController getInstance() {
        if (controller == null) {
            controller = new AddressJpaController();
        }
        return controller;
    }

    public AddressJpaController() {
        super(Addresses.class);
    }

    public Addresses create(Addresses entity) {
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

    public void edit(Addresses addresses) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            addresses = em.merge(addresses);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = addresses.getId().intValue();
                if (findContact(id) == null) {
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

    public Addresses findContact(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Addresses.class, id);
        } finally {
            em.close();
        }
    }

    // find contacts by parent types
    public List<Addresses> findAddresses(String parentType) {
        List<Addresses> addressList = new ArrayList<>();
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Addresses.findByParentType");
        query.setParameter("parentType", parentType);
        try {
            addressList = query.getResultList();
            LOG.log(Level.FINE, "offices found for username {0}", new Object[]{parentType});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return addressList;
    }

    // find contacts by parent type and parent id
    public List<Addresses> findAddresses(String parentType, Integer parentId) {
        List<Addresses> addressList = new ArrayList<>();
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Contacts.findByParentTypeANDId");
        query.setParameter("parentType", parentType);
        query.setParameter("parentId", parentId);
        try {
            addressList = query.getResultList();
            LOG.log(Level.FINE, "offices found for username {0}", new Object[]{parentType});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return addressList;
    }

    private List<Addresses> findAddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Addresses.class));
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

    public List<Addresses> findAddressEntities() {
        return findAddressEntities(true, -1, -1);
    }

    public List<Addresses> findAddressEntities(int maxResults, int firstResult) {
        return findAddressEntities(false, maxResults, firstResult);
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Addresses> rt = cq.from(Addresses.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

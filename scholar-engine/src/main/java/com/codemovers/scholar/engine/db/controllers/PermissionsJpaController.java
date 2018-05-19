/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.Permissions;
import com.codemovers.scholar.engine.db.entities.Roles;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author mover
 */
public class PermissionsJpaController  extends EngineJpaController{
    
      protected static final Logger LOG = Logger.getLogger(PermissionsJpaController.class.getName());

    private static PermissionsJpaController controller = null;

    public static PermissionsJpaController getInstance() {
        if (controller == null) {
            controller = new PermissionsJpaController();
        }
        return controller;
    }
    
     public PermissionsJpaController() {
        super(Permissions.class);
    }
     
     
  
    private List<Permissions> findPermissions(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Permissions.class));
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

    public List<Permissions> findPermissions(SchoolData data) {
        return findPermissions(true, -1, -1, data);
    }

    public List<Permissions> findPermissions(int maxResults, int firstResult, SchoolData data) {
        return findPermissions(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Permissions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    
    
}

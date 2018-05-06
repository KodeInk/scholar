/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.RolePermission;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.UserRole;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author mover 5/6/2018
 */
public class RolePermissionJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(RolePermissionJpaController.class.getName());

    private static RolePermissionJpaController controller = null;

    public static RolePermissionJpaController getInstance() {
        if (controller == null) {
            controller = new RolePermissionJpaController();
        }
        return controller;
    }

    public RolePermissionJpaController() {
        super(RolePermission.class);
    }

    public void deleteRolePermission(Long roleId, SchoolData data) {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());

            Query query = em.createQuery("DELETE FROM RolePermission WHERE role_id = " + roleId + " ");

            query.executeUpdate();

        } catch (Exception eml) {
            LOG.log(Level.INFO, eml.toString());
            throw eml;
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public RolePermission create(RolePermission entity, SchoolData data) {
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

    public RolePermission edit(RolePermission role_permission, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            role_permission = em.merge(role_permission);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = role_permission.getId().intValue();
                if (findRole(id, data) == null) {
                    throw new BadRequestException("The Inventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return role_permission;
    }

    public RolePermission findRole(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(RolePermission.class, id);
        } finally {
            em.close();
        }
    }

    public void destroy(Integer id, SchoolData tenantdata) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(tenantdata.getExternalId());
            em.getTransaction().begin();
            RolePermission role_permission;
            try {
                role_permission = em.getReference(RolePermission.class, id.longValue());
                role_permission.getId();
            } catch (EntityNotFoundException enfe) {
                throw enfe;

            }
            em.remove(role_permission);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}

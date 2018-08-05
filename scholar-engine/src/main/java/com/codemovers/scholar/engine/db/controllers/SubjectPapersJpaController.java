/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.SubjectPapers;
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
public class SubjectPapersJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(SubjectPapersJpaController.class.getName());

    private static SubjectPapersJpaController controller = null;

    public static SubjectPapersJpaController getInstance() {
        if (controller == null) {
            controller = new SubjectPapersJpaController();
        }
        return controller;
    }

    public SubjectPapersJpaController() {
        super(SubjectPapers.class);
    }

    public SubjectPapers create(SubjectPapers entity, SchoolData data) {
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

    public void edit(SubjectPapers subjectPapers, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            subjectPapers = em.merge(subjectPapers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subjectPapers.getId().intValue();
                if (findSubjectPaper(id, data) == null) {
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

    public SubjectPapers findSubjectPaper(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(SubjectPapers.class, id);
        } finally {
            em.close();
        }
    }

    private List<SubjectPapers> findSubjectPapers(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubjectPapers.class));
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
    
    public List<SubjectPapers> findSubjectpapers(String name, String code,Integer subject_id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<SubjectPapers> list = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("SubjectPapers.findByNameOrCode");
            query.setParameter("name", name);
            query.setParameter("code", code);
            query.setParameter("id", subject_id.longValue());
            

            list = query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }

        return list;
    }
    

    public List<SubjectPapers> findSubjectPapers(SchoolData data) {
        return findSubjectPapers(true, -1, -1, data);
    }

    public List<SubjectPapers> findSubjectPapers(int maxResults, int firstResult, SchoolData data) {
        return findSubjectPapers(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubjectPapers> rt = cq.from(SubjectPapers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import static com.codemovers.scholar.engine.db.controllers.GradingJpaController.LOG;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Grading;
import com.codemovers.scholar.engine.db.entities.GradingDetails;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.expr.Instanceof;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author Manny
 */
public class GradingDetailsJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(GradingDetailsJpaController.class.getName());

    private static GradingDetailsJpaController controller = null;

    public static GradingDetailsJpaController getInstance() {
        if (controller == null) {
            controller = new GradingDetailsJpaController();
        }
        return controller;
    }

    public GradingDetailsJpaController() {
        super(GradingDetails.class);
    }

    public GradingDetails create(GradingDetails entity, SchoolData data) {
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

    public void edit(GradingDetails gradingDetails, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            gradingDetails = em.merge(gradingDetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gradingDetails.getId().intValue();
                if (findGradingDetail(id, data) == null) {
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

    public GradingDetails findGradingDetail(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            return em.find(GradingDetails.class, id);
        } finally {
            em.close();
        }
    }

    private List<GradingDetails> findGradingDetailEntities(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GradingDetails.class));
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

    public List<GradingDetails> findGradingDetailEntities(SchoolData data) {
        return findGradingDetailEntities(true, -1, -1, data);
    }

    public List<GradingDetails> findGradingDetailEntities(int maxResults, int firstResult, SchoolData data) {
        return findGradingDetailEntities(false, maxResults, firstResult, data);
    }

    public List<GradingDetails> findIfGradingDetailsExists(String symbol, Integer min, Integer max, Integer gradingScale, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<GradingDetails> list = new ArrayList<>();
        try {
            Query query = findIfGradingDetailsExistsQuery(em, symbol, max, min, gradingScale);
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

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GradingDetails> rt = cq.from(GradingDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Query findIfGradingDetailsExistsQuery(EntityManager em, String queryString, Integer max, Integer min, Integer gradingScale) {

        Query query = em.createQuery(""
                + "select GD FROM GradingDetails GD "
                + "LEFT JOIN GD.gradingScale GS "
                + "WHERE "
                + " ( GD.symbol LIKE :symbol "
                + " OR"
                + " ("
                + " GD.mingrade <= :mingrade "
                + " AND GD.maxgrade >= :maxgrade "
                + " ) "
                + "  ) AND GS.id = :id "
                + "");

        query.setParameter("symbol", "%" + queryString + "%");       
        query.setParameter("mingrade", min.longValue());
        query.setParameter("maxgrade", max.longValue());
        query.setParameter("id", gradingScale.longValue());

        return query;

    }

}

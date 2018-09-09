/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.helper.Utilities;
import java.util.ArrayList;
import java.util.Date;
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
public class TermsJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(TermsJpaController.class.getName());

    private static TermsJpaController controller = null;

    public static TermsJpaController getInstance() {
        if (controller == null) {
            controller = new TermsJpaController();
        }
        return controller;
    }

    public TermsJpaController() {
        super(Terms.class);
    }

    /**
     *
     * @param entity
     * @param data
     * @return
     */
    public Terms create(Terms entity, SchoolData data) {
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

    /**
     *
     * @param terms
     * @param data
     * @return
     * @throws Exception
     */
    public Terms edit(Terms terms, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            terms = em.merge(terms);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = terms.getId().intValue();
                if (findTerm(id, data) == null) {
                    throw new BadRequestException("The Inventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return terms;
    }

    /**
     *
     * @param id
     * @param data
     * @return
     */
    public Terms findTerm(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            return em.find(Terms.class, id.longValue());
        } finally {
            em.close();
        }
    }

    private List<Terms> findTerms(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Terms.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        } finally {
            em.close();
        }
    }

    public List<Terms> findTerms(Integer studyYear, int maxResults, int firstResult, SchoolData data) {

        List<Terms> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            Query query = em.createNamedQuery("Terms.findByStudyYear");
            query.setParameter("id", Long.valueOf(studyYear));

            query.setMaxResults(maxResults);
            query.setFirstResult(firstResult);

            termsList = query.getResultList();
            LOG.log(Level.FINE, "Term  in study Year  {0}", new Object[]{studyYear});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }

        return termsList;
    }

    /**
     *
     * @param data
     * @return
     */
    public List<Terms> findTerms(SchoolData data) {
        return findTerms(true, -1, -1, data);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @param data
     * @return
     */
    public List<Terms> findTerms(int maxResults, int firstResult, SchoolData data) {
        return findTerms(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Terms> rt = cq.from(Terms.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param ranking
     * @param studyYearId
     * @param data
     * @return
     */
    public List<Terms> findTermByRank(Long ranking, Long studyYearId, SchoolData data) {
        List<Terms> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            Query query = em.createNamedQuery("Terms.findByRanking");
            query.setParameter("ranking", ranking);
            query.setParameter("studyYearId", studyYearId);
            termsList = query.getResultList();
            LOG.log(Level.FINE, "Term foudn with start date e {0}", new Object[]{ranking});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }

        return termsList;
    }

    public List<Terms> checkTermByStartDate(Date startDate, Long studyYearId,Integer term_id, SchoolData data) {
        List<Terms> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("Terms.checkByStartDateOnEdit");
        query.setParameter("startdate", startDate);
        query.setParameter("studyYearId", studyYearId);
        query.setParameter("id", term_id.longValue());
        try {
            termsList = query.getResultList();
            LOG.log(Level.FINE, "Term foudn with start date e {0}", new Object[]{startDate});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return termsList;
    }

    
    
    /**
     *
     * @param startDate
     * @param studyYearId
     * @param data
     * @return
     */
    public List<Terms> checkTermByStartDate(Date startDate, Long studyYearId, SchoolData data) {
        List<Terms> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("Terms.checkByStartDate");
        query.setParameter("startdate", startDate);
        query.setParameter("studyYearId", studyYearId);
        try {
            termsList = query.getResultList();
            LOG.log(Level.FINE, "Term foudn with start date e {0}", new Object[]{startDate});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return termsList;
    }

    /**
     *
     * @param endDate
     * @param studyYearId
     * @param data
     * @return
     */
    public List<Terms> checkTermByEndDate(Date endDate, Long studyYearId, SchoolData data) {
        List<Terms> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("Terms.checkByEndDate");
        query.setParameter("enddate", endDate);
        query.setParameter("studyYearId", studyYearId);
        try {
            termsList = query.getResultList();
            LOG.log(Level.FINE, "Term foudn with start date e {0}", new Object[]{endDate});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return termsList;
    }

    
     public List<Terms> checkTermByEndDate(Date endDate, Long studyYearId,Integer term_id, SchoolData data) {
        List<Terms> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("Terms.checkByEndDateOnEdit");
        query.setParameter("enddate", endDate);
        query.setParameter("studyYearId", studyYearId);
        query.setParameter("id", term_id.longValue());
        try {
            termsList = query.getResultList();
            LOG.log(Level.FINE, "Term foudn with start date e {0}", new Object[]{endDate});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return termsList;
    }

     
     
    /**
     *
     * @param startDate
     * @param endDate
     * @param studyYearId
     * @param data
     * @return
     */
    public List<Terms> checkTermByStartAndEndDate(Date startDate, Date endDate, Long studyYearId, SchoolData data) {
        List<Terms> termsList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("Terms.checkByStartAndEndDate");
        query.setParameter("startdate", startDate);
        query.setParameter("enddate", endDate);
        query.setParameter("studyYearId", studyYearId);
        try {
            termsList = query.getResultList();
            LOG.log(Level.FINE, "Term foudn with start date e {0}", new Object[]{endDate});
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }
        return termsList;
    }

    /**
     *
     * @param studyYearid
     * @param studyYearId
     * @param maxResults
     * @param firstResult
     * @param data
     * @return
     */
    public List<Terms> findTermsByStudyYear(Integer studyYearid, Long studyYearId, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Terms> list = new ArrayList<>();
        try {
            Query query = getQuery(em, studyYearid);
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

    /**
     *
     * @param searchQuery
     * @param maxResults
     * @param firstResult
     * @param data
     * @return
     */
    public List<Terms> searchTerm(String searchQuery, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        List<Terms> list = new ArrayList<>();
        try {
            Query query = getQuery(em, searchQuery);
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

    /**
     *
     * @param em
     * @param studyYear
     * @return
     */
    public Query getQuery(EntityManager em, Integer studyYear) {
        Query query = em.createQuery(""
                + "select ST FROM Terms TM"
                + "LEFT JOIN TM.studyYear SY"
                + " WHERE  SY.id = :STUDYYEAR");
        query.setParameter("STUDYYEAR", studyYear.longValue());
        return query;

    }

    public Query getQuery(EntityManager em, String searchQuery) {
        Query query = em.createQuery(""
                + " select TM FROM Terms TM "
                + " LEFT JOIN TM.studyYear SY"
                + " WHERE  ( SY.theme LIKE :THEME"
                + " OR TM.name LIKE :NAME "
                + " ) ");
        query.setParameter("THEME", "%" + searchQuery + "%");
        query.setParameter("NAME", "%" + searchQuery + "%");
        return query;

    }

}

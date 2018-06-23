/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.JpaController;
import static com.codemovers.scholar.engine.db.controllers.UsersJpaController.LOG;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentSubjectRegistration;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.Users;
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
 * @author mover 6/23/2018
 */
public class StudentTermRegistrationJpaController extends EngineJpaController {

    protected static final Logger LOG = Logger.getLogger(StudentTermRegistrationJpaController.class.getName());

    private static StudentTermRegistrationJpaController controller = null;

    public static StudentTermRegistrationJpaController getInstance() {
        if (controller == null) {
            controller = new StudentTermRegistrationJpaController();
        }
        return controller;
    }

    public StudentTermRegistrationJpaController() {
        super(StudentTermRegistration.class);
    }

    public StudentTermRegistration create(StudentTermRegistration entity, SchoolData data) {
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

    public StudentTermRegistration edit(StudentTermRegistration studentTermRegistration, SchoolData data) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager(data.getExternalId());
            em.getTransaction().begin();
            studentTermRegistration = em.merge(studentTermRegistration);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentTermRegistration.getId().intValue();
                if (findStudentTermRegistration(id, data) == null) {
                    throw new BadRequestException("The Inventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return studentTermRegistration;
    }

    public StudentTermRegistration findStudentTermRegistration(Integer id, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());

        try {
            StudentTermRegistration registration =  em.find(StudentTermRegistration.class, id.longValue());
            return registration;
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    //todo: find term registration by student_id and term_id
    public StudentTermRegistration findStudentTermRegistration(Integer student_id, Integer term_id, SchoolData data) {
        List<StudentTermRegistration> studentTermRegistrationList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("StudentTermRegistration.findByAdmissionIdAndTermId");
        query.setParameter("registration_term_id", term_id);
        query.setParameter("student_id", student_id);

        try {
            studentTermRegistrationList = query.getResultList();
            if (studentTermRegistrationList.size() > 0) {
                return studentTermRegistrationList.get(0);
            }
            return null;

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }

    }

    /**
     *
     * @param class_id
     * @param term_id
     * @param admissin_id
     * @param data
     * @return
     */
    public StudentTermRegistration findStudentByTermAndClassAndAdmission(Long class_id, Long term_id, Long admissin_id, SchoolData data) {
        List<StudentTermRegistration> studentTermRegistrationList = new ArrayList<>();
        EntityManager em = getEntityManager(data.getExternalId());
        Query query = em.createNamedQuery("StudentTermRegistration.findByAdmissionAndTermAndClass");
        query.setParameter("termId", term_id);
        query.setParameter("admissionId", admissin_id);
        query.setParameter("classId", class_id);

        try {
            studentTermRegistrationList = query.getResultList();
            if (studentTermRegistrationList.size() > 0) {
                return studentTermRegistrationList.get(0);
            }
            return null;

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "unexpected exception {0}\n{1}", new Object[]{ex.getMessage(), Utilities.getStackTrace(ex)});
            return null;
            // don't throw WebApplicationException, force caller to handle this
        } finally {
            LOG.log(Level.FINER, "closing entity manager {0}", em);
            em.close();
        }

    }

    private List<StudentTermRegistration> findStudentTermRegistrations(boolean all, int maxResults, int firstResult, SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentTermRegistration.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }catch(Exception er){
            er.printStackTrace();
            throw er;
        } 
        finally {
            em.close();
        }
       
    }

    public List<StudentTermRegistration> findStudentTermRegistrations(SchoolData data) {
        return findStudentTermRegistrations(true, -1, -1, data);
    }

    public List<StudentTermRegistration> findStudentTermRegistrations(int maxResults, int firstResult, SchoolData data) {
        return findStudentTermRegistrations(false, maxResults, firstResult, data);
    }

    public int getCount(SchoolData data) {
        EntityManager em = getEntityManager(data.getExternalId());
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentTermRegistration> rt = cq.from(StudentTermRegistration.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Integer) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}

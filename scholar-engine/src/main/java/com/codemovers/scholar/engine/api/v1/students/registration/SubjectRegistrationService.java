/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.students.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.entities._Admission;
import com.codemovers.scholar.engine.api.v1.students.registration.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.db.controllers.StudentAdmissionJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentExamRegistrationJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 6/19/2018
 */
public class SubjectRegistrationService  extends AbstractService<_TermRegistration, TermRegistrationResponse> {

       private static final Logger LOG = Logger.getLogger(AdmissionService.class.getName());
    private final StudentExamRegistrationJpaController controller;
    private static SubjectRegistrationService service = null;
    
     public SubjectRegistrationService() {
        controller = StudentExamRegistrationJpaController.getInstance();
    }
     
      public static SubjectRegistrationService getInstance() {
        if (service == null) {
            service = new SubjectRegistrationService();
        }
        return service;
    }

    @Override
    public AdmissionResponse create(SchoolData data, _Admission entity, AuthenticationResponse authentication) throws Exception {
        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmissionResponse update(SchoolData data, _Admission entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmissionResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdmissionResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

      
      
      
}

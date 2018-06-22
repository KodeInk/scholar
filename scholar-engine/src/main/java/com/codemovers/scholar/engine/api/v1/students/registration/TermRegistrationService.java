/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.students.admissions.AdmissionService;
import com.codemovers.scholar.engine.api.v1.students.registration.entities.TermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.registration.entities._TermRegistration;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import java.util.logging.Logger;

/**
 *
 * @author mover
 */
public class TermRegistrationService extends AbstractService<_TermRegistration, TermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(AdmissionService.class.getName());
    private final StudentTermRegistrationJpaController controller;
    private static TermRegistrationService instance = null;

    public TermRegistrationService() {
        controller = StudentTermRegistrationJpaController.getInstance();
    }

    public static TermRegistrationService getInstance() {
        if (instance == null) {
            instance = new TermRegistrationService();
        }

        return instance;

    }

}

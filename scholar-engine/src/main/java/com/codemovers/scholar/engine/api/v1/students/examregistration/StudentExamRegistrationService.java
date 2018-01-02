/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.examregistration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.students.admission.StudentAdmissionServiceInterface.ADMIT_STUDENT_PERMISSION;
import com.codemovers.scholar.engine.api.v1.students.examregistration.entities.StudentExamRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.examregistration.entities._StudentExamRegistration;
import com.codemovers.scholar.engine.db.controllers.StudentExamRegistrationJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 1/2/2018
 */
public class StudentExamRegistrationService extends AbstractService<_StudentExamRegistration, StudentExamRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(StudentExamRegistrationService.class.getName());
    private final StudentExamRegistrationJpaController controller;
    private static StudentExamRegistrationService service = null;

    final String[] CREATE_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_EXAMREGISTRATION"};
    final String[] UPDATE_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_EXAMREGISTRATION"};
    final String[] LIST_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_EXAMREGISTRATION"};
    final String[] ARCHIVE_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_EXAMREGISTRATION"};


    public StudentExamRegistrationService() {
        controller = StudentExamRegistrationJpaController.getInstance();
    }

    public static StudentExamRegistrationService getInstance() {
        if (service == null) {
            service = new StudentExamRegistrationService();
        }
        return service;
    }

    @Override
    public StudentExamRegistrationResponse create(SchoolData data, _StudentExamRegistration entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_EXAMREGISTRATION_PERMISSION);

        return super.create(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentExamRegistrationResponse update(SchoolData data, _StudentExamRegistration entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_EXAMREGISTRATION_PERMISSION);
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentExamRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_EXAMREGISTRATION_PERMISSION);
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentExamRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(LIST_EXAMREGISTRATION_PERMISSION);
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentExamRegistrationResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_EXAMREGISTRATION_PERMISSION);
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }


}

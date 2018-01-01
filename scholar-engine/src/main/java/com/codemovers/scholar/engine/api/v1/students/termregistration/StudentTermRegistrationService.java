/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.termregistration;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.streams.StreamsService;
import static com.codemovers.scholar.engine.api.v1.students.admission.StudentAdmissionServiceInterface.ADMIT_STUDENT_PERMISSION;
import com.codemovers.scholar.engine.api.v1.students.termregistration.entities.StudentTermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.termregistration.entities._StudentTermRegistration;
import com.codemovers.scholar.engine.db.controllers.ClassJpaController;
import com.codemovers.scholar.engine.db.controllers.StreamsJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentAdmissionJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentTermRegistrationJpaController;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 1/1/2018
 */
public class StudentTermRegistrationService extends AbstractService<_StudentTermRegistration, StudentTermRegistrationResponse> {

    private static final Logger LOG = Logger.getLogger(StreamsService.class.getName());
    private final StudentTermRegistrationJpaController controller;
    private static StudentTermRegistrationService service = null;

    final String[] REGISTER_STUDENT_TERMT_PERMISSION = new String[]{"ALL_FUNCTIONS", "REGISTER_STUDENT_TERM"};


    public StudentTermRegistrationService() {
        controller = StudentTermRegistrationJpaController.getInstance();
    }

    public static StudentTermRegistrationService getInstance() {
        if (service == null) {
            service = new StudentTermRegistrationService();
        }
        return service;
    }

    @Override
    public StudentTermRegistrationResponse create(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception {
        check_access(REGISTER_STUDENT_TERMT_PERMISSION);
        entity.validate();

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: get the admission by id;
          StudentAdmission admission = StudentAdmissionJpaController.getInstance().findStudentAdmission(entity.getAdmission_id(), data);
        //TODO: get term by id
        Terms registration_term = TermsJpaController.getInstance().findTerm(entity.getTerm_id(), data);
        //TODO: get term by id
        Classes RegistrationClass = ClassJpaController.getInstance().findClass(entity.getClass_id(), data);

        //Todo: get Stream by Id
        Streams RegistrationStream = StreamsJpaController.getInstance().findStream(entity.getStream_id(), data);


        StudentTermRegistration registration = new StudentTermRegistration();

        registration.setRegistration_term(registration_term);
        registration.setRegistration_Class(RegistrationClass);
        registration.setRegistration_Stream(RegistrationStream);
        registration.setStudent_Admission(admission);

        //todo: add user
        registration.setAuthor(new Users(entity.getAuthor_id().longValue()));
        registration.setStatus(entity.getStatus().toString());

        registration = controller.create(registration, data);

        return populateResponse(registration);
    }

    @Override
    public StudentTermRegistrationResponse update(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentTermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentTermRegistrationResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentTermRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    public StudentTermRegistrationResponse populateResponse(StudentTermRegistration entity) {
        StudentTermRegistrationResponse response = new StudentTermRegistrationResponse();

        return response;
    }

}

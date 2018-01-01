/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.admission;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.profile.ProfileService;
import com.codemovers.scholar.engine.api.v1.profile.entities.ProfileResponse;
import com.codemovers.scholar.engine.api.v1.streams.StreamsService;
import com.codemovers.scholar.engine.api.v1.students.admission.entities.StudentAdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities._StudentAdmission;
import com.codemovers.scholar.engine.db.controllers.ClassJpaController;
import com.codemovers.scholar.engine.db.controllers.ProfileJpaController;
import com.codemovers.scholar.engine.db.controllers.StreamsJpaController;
import com.codemovers.scholar.engine.db.controllers.StudentAdmissionJpaController;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.Terms;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import static com.codemovers.scholar.engine.helper.Utilities.getNewExternalId;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/30/2017
 */
public class StudentAdmissionService extends AbstractService<_StudentAdmission, StudentAdmissionResponse> {

    private static final Logger LOG = Logger.getLogger(StreamsService.class.getName());
    private final StudentAdmissionJpaController controller;
    private static StudentAdmissionService service = null;

    final String[] ADMIT_STUDENT_PERMISSION = new String[]{"ALL_FUNCTIONS", "ADMIT_STUDENT"};
    final String[] UPDATE_STUDENT_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_STUDENT"};


    public StudentAdmissionService() {
        controller = StudentAdmissionJpaController.getInstance();
    }

    public static StudentAdmissionService getInstance() {
        if (service == null) {
            service = new StudentAdmissionService();
        }
        return service;
    }

    @Override
    public StudentAdmissionResponse create(SchoolData data, _StudentAdmission entity, AuthenticationResponse authentication) throws Exception {
        check_access(ADMIT_STUDENT_PERMISSION);
        entity.validate();
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: checkt to see if there is no account with this admision number :: 

        //todo: create a profile_by id
        ProfileResponse pr = ProfileService.getInstance().create(data, entity.getProfile());
        //todo: generate external Id 
        if (entity.getExternal_id().isEmpty()) {
            entity.setExternal_id(getNewExternalId());
        }

        //get profile by Id;
        Profile profile = ProfileJpaController.getInstance().findProfile(pr.getId(), data);
        //todo: get  class by Id

        Classes _class = ClassJpaController.getInstance().findClass(entity.getClass_id(), data);
        //todo: get stream by id
        Streams _stream = StreamsJpaController.getInstance().findStream(entity.getStream_id(), data);
        //todo: get  Term by ID
        Terms _term = TermsJpaController.getInstance().findTerm(entity.getTerm_id(), data);

        StudentAdmission admission = new StudentAdmission();

        //todo: creation pojo ::
        StudentAdmission studentAdmission = new StudentAdmission();
        studentAdmission.setAdmissionNo(entity.getAdmission_number());
        studentAdmission.setExternalId(entity.getExternal_id());
        studentAdmission.setDateOfAdmission(entity.getDate_of_admission());
        studentAdmission.setStatus(entity.getStatus().toString());

        studentAdmission.setAdmissionTerm(_term);

        admission = controller.create(admission, data);
        return populateResponse(admission);

    }

    @Override
    public StudentAdmissionResponse update(SchoolData data, _StudentAdmission entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_STUDENT_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        StudentAdmission admission = controller.findStudentAdmission(entity.getId(), data);
        if (admission == null) {
            throw new BadRequestException("RECORD DOES NOT EXIST ");
        }

        if (entity.getAdmission_number() != null && !entity.getAdmission_number().equalsIgnoreCase(admission.getAdmissionNo())) {
            admission.setAdmissionNo(entity.getAdmission_number());
        }

        if (entity.getDate_of_admission() != null && entity.getDate_of_admission() != (admission.getDateOfAdmission())) {
            admission.setDateOfAdmission(entity.getDate_of_admission());
        }





        return super.update(data, entity, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentAdmissionResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentAdmissionResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentAdmissionResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        return super.list(data, ofset, limit, authentication); //To change body of generated methods, choose Tools | Templates.
    }


    public StudentAdmissionResponse populateResponse(StudentAdmission entity) {
        StudentAdmissionResponse response = new StudentAdmissionResponse();

        return response;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.admissions;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.admissions.entities._Admission;
import com.codemovers.scholar.engine.api.v1.classes.ClassService;
import com.codemovers.scholar.engine.api.v1.profile.ProfileService;
import com.codemovers.scholar.engine.api.v1.profile.entities._Profile;
import com.codemovers.scholar.engine.api.v1.terms.TermService;
import com.codemovers.scholar.engine.db.controllers.StudentAdmissionJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.db.entities.Users;
import com.codemovers.scholar.engine.helper.Utilities;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class AdmissionService extends AbstractService<_Admission, AdmissionResponse> implements AdmissionServiceInterface {

    private static final Logger LOG = Logger.getLogger(AdmissionService.class.getName());
    private final StudentAdmissionJpaController controller;
    private static AdmissionService service = null;

    public AdmissionService() {
        controller = StudentAdmissionJpaController.getInstance();
    }

    public static AdmissionService getInstance() {
        if (service == null) {
            service = new AdmissionService();
        }
        return service;
    }

    @Override
    public AdmissionResponse create(SchoolData data, _Admission entity, AuthenticationResponse authentication) throws Exception {

        //todo: validate the entity 
        entity.validate();

        //todo: check  addmission class
        Classes aclass = ClassService.getInstance().getClass(entity.getClass_id(), data);
        if (aclass == null) {
            throw new BadRequestException("Class does not exist in the system");
        }
        //todo: validate admission term 
        Terms term = TermService.getInstance().getTerm(entity.getTerm_id(), data);

        if (term == null) {
            throw new BadRequestException("Term  does not exist in the system");
        }

        List<StudentAdmission> admissions = getByAdmissionNo(entity.getAdmission_no(), data);
        if (admissions != null && admissions.size() > 0) {
            throw new BadRequestException(" Admission exists with admission number : {0} ", entity.getAdmission_no());
        }

        //todo: populate entity 
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDate_created(new Date().getTime());

        Profile profile = saveStudentProfile(entity, data, authentication);
        StudentAdmission admission = populateEntity(aclass, term, entity, profile);
        //todo: save entity
        admission = controller.create(admission, data);
        //todo: response body 

        AdmissionResponse admissionResponse = populateResponse(admission);

        return admissionResponse;
    }

    @Override
    public AdmissionResponse update(SchoolData data, _Admission entity, AuthenticationResponse authentication) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmissionResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {

        return super.archive(data, id);
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param data
     * @param Id
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public AdmissionResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        if (Id == null) {
            throw new BadRequestException("Student Id is null");
        }

        StudentAdmission studentAdmission = controller.findStudentAdmission(Id, data);
        if (studentAdmission == null) {
            throw new BadRequestException("Sudent Admission does not exist with that ID ");
        }

        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param admissioNo
     * @param data
     * @return
     */
    @Override
    public List<StudentAdmission> getByAdmissionNo(String admissioNo, SchoolData data) {
        //todo: check if there is no admission with the same admission no
        if (admissioNo.isEmpty()) {
            throw new BadRequestException("Student Admission Number is Empty");
        }
        List<StudentAdmission> admissions = controller.findStudentAdmission(admissioNo, data);
        if (admissions == null || admissions.isEmpty()) {
            throw new BadRequestException("Sudent Admission does not exist with  student number {0} ", admissioNo);
        }

        return admissions;
    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<AdmissionResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        List<StudentAdmission> admissions = controller.findStudentAdmissions(limit, ofset, data);
        List<AdmissionResponse> admissionResponses = new ArrayList<>();
        admissions.forEach(admission -> {
            admissionResponses.add(populateResponse(admission));
        });
        return admissionResponses;
    }

    /**
     *
     * @param entity
     * @param data
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public Profile saveStudentProfile(_Admission entity, SchoolData data, AuthenticationResponse authentication) throws Exception {
        _Profile studentProfile = entity.getStudent();
        studentProfile.validate();
        Profile profile = ProfileService.getInstance().getProfile(studentProfile);
        profile = ProfileService.getInstance().create(data, profile, authentication);
        return profile;
    }

    /**
     *
     * @param aclass
     * @param term
     * @param entity
     * @param profile
     * @return
     */
    @Override
    public StudentAdmission populateEntity(Classes aclass, Terms term, _Admission entity, Profile profile) {
        StudentAdmission admission = new StudentAdmission();
        admission.setAdmissionClass(aclass);
        admission.setAdmissionTerm(term);
        admission.setAdmissionNo(entity.getAdmission_no());
        admission.setExternalId(Utilities.getNewExternalId());
        admission.setDateOfAdmission(new Date(entity.getDate_of_admission()));
        admission.setProfile(profile);
        admission.setDateCreated(new Date(entity.getDate_created()));
        admission.setStatus(entity.getStatus().name());
        admission.setAuthor(new Users(entity.getAuthor_id().longValue()));
        return admission;
    }

    /**
     *
     * @param admission
     * @return
     */
    @Override
    public AdmissionResponse populateResponse(StudentAdmission admission) {
        AdmissionResponse admissionResponse = new AdmissionResponse();
        admissionResponse.setId(admission.getId().intValue());
        admissionResponse.setStudent(ProfileService.getInstance().populateResponse(admission.getProfile()));
        admissionResponse.setAdmission_no(admission.getAdmissionNo());
        admissionResponse.setExternal_id(admission.getExternalId());
        admissionResponse.setDate_of_admission(admission.getDateOfAdmission().getTime());
        admissionResponse.setAdmissionClass(ClassService.getInstance().populateResponse(admission.getAdmissionClass()));
        admissionResponse.setAdmissionStream(null);
        admissionResponse.setAdmissionTerm(TermService.getInstance().populateResponse(admission.getAdmissionTerm()));
        admissionResponse.setStatus(admission.getStatus());
        admissionResponse.setDate_created(admission.getDateCreated().getTime());
        admissionResponse.setAuthor(admission.getAuthor().getUsername());
        return admissionResponse;
    }

}

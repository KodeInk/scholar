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
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class AdmissionService extends AbstractService<_Admission, AdmissionResponse> {

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

        //todo: check if there is no admission with the same admission no 
        List<StudentAdmission> admissions = controller.findStudentAdmission(entity.getAdmission_no(), data);
        if (admissions != null && admissions.size() > 0) {
            throw new BadRequestException(" Admission exists with admission number : {0} ", entity.getAdmission_no());
        }

        //todo: populate entity 
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDate_created(new Date().getTime());

        Profile profile = saveStudentProfile(entity, data, authentication);

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

        //todo: save entity
        //todo: response body 
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    public Profile saveStudentProfile(_Admission entity, SchoolData data, AuthenticationResponse authentication) throws Exception {
        //todo: create profile
        _Profile studentProfile = entity.getStudent();
        studentProfile.validate();
        Profile profile = null;
        profile = ProfileService.getInstance().getProfile(studentProfile);
        profile = ProfileService.getInstance().create(data, profile, authentication);
        return profile;
    }

    @Override
    public AdmissionResponse update(SchoolData data, _Admission entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmissionResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmissionResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdmissionResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

}

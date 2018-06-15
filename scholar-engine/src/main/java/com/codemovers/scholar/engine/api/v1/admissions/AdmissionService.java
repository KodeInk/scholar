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
import com.codemovers.scholar.engine.api.v1.terms.TermService;
import com.codemovers.scholar.engine.db.controllers.StudentAdmissionJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
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
        //todo: populate entity 
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

//        StudentAdmission admission = new StudentAdmission();
//        admission.setAdmissionClass(enti);
        //todo: save entity
        //todo: response body 
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
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

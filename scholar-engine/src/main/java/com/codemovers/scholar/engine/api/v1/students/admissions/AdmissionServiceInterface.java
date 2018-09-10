/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.admissions;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.admissions.entities.Admission;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import com.codemovers.scholar.engine.db.entities.Terms;
import java.util.List;

/**
 *
 * @author mover 6/15/2018
 */
public interface AdmissionServiceInterface {

    AdmissionResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception;

    AdmissionResponse create(SchoolData data, Admission entity, AuthenticationResponse authentication) throws Exception;

    /**
     *
     * @param admissioNo
     * @param data
     * @return
     */
    List<StudentAdmission> getByAdmissionNo(String admissioNo, SchoolData data);

    /**
     *
     * @param data
     * @param Id
     * @param authentication
     * @return
     * @throws Exception
     */
    AdmissionResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception;

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    List<AdmissionResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception;

    /**
     *
     * @param aclass
     * @param term
     * @param entity
     * @param profile
     * @return
     */
    StudentAdmission populateEntity(Classes aclass, Terms term, Admission entity, Profile profile);

    /**
     *
     * @param admission
     * @return
     */
    AdmissionResponse populateResponse(StudentAdmission admission);

    /**
     *
     * @param entity
     * @param data
     * @param authentication
     * @return
     * @throws Exception
     */
    Profile saveStudentProfile(Admission entity, SchoolData data, AuthenticationResponse authentication) throws Exception;

    AdmissionResponse update(SchoolData data, Admission entity, AuthenticationResponse authentication) throws Exception;
    
}

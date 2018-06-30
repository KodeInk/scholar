/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.terms;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.terms.entities.StudentTermRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.terms.entities._StudentTermRegistration;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentTermRegistration;
import java.util.List;

/**
 *
 * @author mover 1/1/2018
 */
public interface StudentTermRegistrationServiceInterface {

    final String[] REGISTER_STUDENT_TERM_PERMISSION = new String[]{"ALL_FUNCTIONS", "REGISTER_STUDENT_TERM"};
    final String[] UPDATE_STUDENT_TERM_REGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_STUDENT_TERM_REGISTRATION"};
    final String[] ARCHIVE_STUDENT_TERM_REGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_STUDENT_TERM_REGISTRATION"};
    final String[] LIST_STUDENT_TERM_REGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_STUDENT_TERM_REGISTRATION"};

    StudentTermRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception;

    StudentTermRegistrationResponse create(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception;

    StudentTermRegistrationResponse getById(SchoolData data, Integer Id) throws Exception;

    List<StudentTermRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception;

    StudentTermRegistrationResponse populateResponse(StudentTermRegistration entity);

    StudentTermRegistrationResponse update(SchoolData data, _StudentTermRegistration entity, AuthenticationResponse authentication) throws Exception;

}

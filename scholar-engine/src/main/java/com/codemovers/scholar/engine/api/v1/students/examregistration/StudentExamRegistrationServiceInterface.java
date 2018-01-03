/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.examregistration;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.examregistration.entities.StudentExamRegistrationResponse;
import com.codemovers.scholar.engine.api.v1.students.examregistration.entities._StudentExamRegistration;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentExamRegistration;
import java.util.List;

/**
 *
 * @author mover 1/3/2018
 */
public interface StudentExamRegistrationServiceInterface {

    final String[] CREATE_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_EXAMREGISTRATION"};
    final String[] UPDATE_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_EXAMREGISTRATION"};
    final String[] LIST_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_EXAMREGISTRATION"};
    final String[] ARCHIVE_EXAMREGISTRATION_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_EXAMREGISTRATION"};

    StudentExamRegistrationResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception;

    StudentExamRegistrationResponse create(SchoolData data, _StudentExamRegistration entity, AuthenticationResponse authentication) throws Exception;

    StudentExamRegistrationResponse getById(SchoolData data, Integer Id) throws Exception;

    List<StudentExamRegistrationResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception;

    StudentExamRegistrationResponse populateResponse(StudentExamRegistration entity);

    StudentExamRegistrationResponse update(SchoolData data, _StudentExamRegistration entity, AuthenticationResponse authentication) throws Exception;

}

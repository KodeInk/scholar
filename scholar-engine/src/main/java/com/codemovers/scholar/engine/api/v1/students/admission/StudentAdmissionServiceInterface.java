/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.admission;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities.StudentAdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities._StudentAdmission;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudentAdmission;
import java.util.List;

/**
 *
 * @author mover 1/1/2018
 */
public interface StudentAdmissionServiceInterface {

    final String[] ADMIT_STUDENT_PERMISSION = new String[]{"ALL_FUNCTIONS", "ADMIT_STUDENT_ADMISSION"};
    final String[] UPDATE_STUDENT_ADMISSION_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_STUDENT_ADMISSION"};
    final String[] ARCHIVE_STUDENT_ADMISSION_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_STUDENT_ADMISSION"};
    final String[] LIST_STUDENT_ADMISSION_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_STUDENT_ADMISSION_PERMISSION"};

    StudentAdmissionResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception;

    StudentAdmissionResponse create(SchoolData data, _StudentAdmission entity, AuthenticationResponse authentication) throws Exception;

    StudentAdmissionResponse getById(SchoolData data, Integer Id) throws Exception;

    List<StudentAdmissionResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception;

    StudentAdmissionResponse populateResponse(StudentAdmission entity);

    StudentAdmissionResponse update(SchoolData data, _StudentAdmission entity, AuthenticationResponse authentication) throws Exception;

}

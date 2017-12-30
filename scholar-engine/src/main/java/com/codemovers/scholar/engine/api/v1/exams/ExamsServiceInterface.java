/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.exams;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities.ExamResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities._Exam;
import com.codemovers.scholar.engine.db.entities.Exams;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;

/**
 *
 * @author mover 12/20/2017
 */
public interface ExamsServiceInterface {

    final String[] CREATE_EXAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_EXAM"};
    final String[] UPDATE_EXAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_EXAM"};
    final String[] ARCHIVE_EXAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_EXAM"};
    final String[] LIST_EXAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_EXAM"};


    ExamResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception;

    ExamResponse create(SchoolData data, _Exam entity, AuthenticationResponse authentication) throws Exception;

    ExamResponse getById(SchoolData data, Integer Id) throws Exception;

    List<ExamResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception;

    ExamResponse populateResponse(Exams entity);

    ExamResponse update(SchoolData data, _Exam entity, AuthenticationResponse authentication) throws Exception;

}

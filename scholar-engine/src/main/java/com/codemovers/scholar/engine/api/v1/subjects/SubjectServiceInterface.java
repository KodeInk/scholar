/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectResponse;
import com.codemovers.scholar.engine.api.v1.subjects.entities._Subject;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Subjects;
import java.util.List;

/**
 *
 * @author mover 1/3/2017
 */
public interface SubjectServiceInterface {

    final String[] CREATE_SUBJECT_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_SUBJECT"};
    final String[] UPDATE_SUBJECT_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_SUBJECT"};

    final String[] ARCHIVE_SUBJECT_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_SUBJECT"};
    final String[] LIST_SUBJECT_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_SUBJECT"};

    SubjectResponse archive(SchoolData data, Integer id) throws Exception;

    SubjectResponse create(SchoolData data, _Subject entity, AuthenticationResponse authentication) throws Exception;

    SubjectResponse getById(SchoolData data, Integer Id) throws Exception;

    List<SubjectResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception;

    SubjectResponse populateResponse(Subjects entity);

    SubjectResponse update(SchoolData data, _Subject entity) throws Exception;

}

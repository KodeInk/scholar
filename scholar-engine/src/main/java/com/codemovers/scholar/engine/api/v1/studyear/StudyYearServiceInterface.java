/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.studyear;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.studyear.entities.StudyYearResponse;
import com.codemovers.scholar.engine.api.v1.studyear.entities._StudyYear;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudyYear;
import java.util.List;

/**
 *
 * @author mover 12/20/2017
 */
public interface StudyYearServiceInterface {

    final String[] CREATE_STUDYEAR_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_STUDYEAR"};
    final String[] UPDATE_STUDYEAR_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_STUDYEAR"};
    final String[] ARCHIVE_STUDYEAR_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_STUDYEAR"};
    final String[] LIST_STUDYEAR_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_STUDYEAR"};


    StudyYearResponse archive(SchoolData data, Integer id) throws Exception;

    StudyYearResponse create(SchoolData data, _StudyYear entity, AuthenticationResponse authentication) throws Exception;

    StudyYearResponse getById(SchoolData data, Integer Id) throws Exception;

    List<StudyYearResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception;

    StudyYearResponse populateResponse(StudyYear entity);

    StudyYearResponse update(SchoolData data, _StudyYear entity, AuthenticationResponse authentication) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.details;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities.CurriculumDetailResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities._CurriculumDetail;
import com.codemovers.scholar.engine.db.entities.CurriculumDetails;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;

/**
 *
 * @author mover 12/28/2017
 */
public interface CurriculumDetailServiceInterface {

    final String[] CREATE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CURRICULUMDETAIL"};
    final String[] UPDATE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_CURRICULUMDETAIL"};
    final String[] ARCHIVE_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_CURRICULUMDETAIL"};
    final String[] LIST_CURRICULUMDETAIL_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_CURRICULUMDETAIL"};


    CurriculumDetailResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception;

    CurriculumDetailResponse create(SchoolData data, _CurriculumDetail entity, AuthenticationResponse authentication) throws Exception;

    CurriculumDetailResponse getById(SchoolData data, Integer Id) throws Exception;

    List<CurriculumDetailResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception;

    CurriculumDetailResponse populateResponse(CurriculumDetails entity);

    CurriculumDetailResponse update(SchoolData data, _CurriculumDetail entity, AuthenticationResponse authentication) throws Exception;

}

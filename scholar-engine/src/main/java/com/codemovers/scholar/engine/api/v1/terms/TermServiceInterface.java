/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.terms;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities._Term;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Terms;
import java.util.List;

/**
 *
 * @author Manny
 */
public interface TermServiceInterface {

    final String[] CREATE_TERM_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_TERM"};
    final String[] UPDATE_TERM_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_TERM"};
    final String[] ARCHIVE_TERM_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_TERM"};
    final String[] LIST_TERM_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_TERM"};


    TermResponse archive(SchoolData data, Integer id) throws Exception;

    TermResponse create(SchoolData data, _Term entity, AuthenticationResponse authentication) throws Exception;

    TermResponse getById(SchoolData data, Integer Id) throws Exception;

    List<TermResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception;

    TermResponse populateResponse(Terms entity);

    TermResponse update(SchoolData data, _Term entity) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.classes;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.classes.entities._Class;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;

/**
 *
 * @author mover 12/27/2017
 */
public interface ClassServiceInterface {

    final String[] CREATE_CLASS_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_CLASS"};
    final String[] LIST_CLASSES_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_CLASSES"};
    final String[] UPDATE_CLASS_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_CLASS"};
    final String[] ARCHIVE_CLASS_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCHIVE_CLASS"};


    ClassResponse archive(SchoolData data, Integer id) throws Exception;

    ClassResponse create(SchoolData data, _Class entity, AuthenticationResponse authentication) throws Exception;

    ClassResponse delete(SchoolData data, Integer id) throws Exception;

    ClassResponse getById(SchoolData data, Integer Id) throws Exception;

    List<ClassResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception;

    ClassResponse populateResponse(Classes entity);

    ClassResponse update(SchoolData data, _Class entity, AuthenticationResponse authentication) throws Exception;

}

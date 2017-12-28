/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.streams;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities.StreamResponse;
import com.codemovers.scholar.engine.api.v1.streams.entities._Stream;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Streams;
import java.util.List;

/**
 *
 * @author mover 12/28/2017
 */
public interface StreamsServiceInterface {

    final String[] CREATE_STREAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_STREAM"};
    final String[] UPDATE_STREAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "UPDATE_STREAM"};
    final String[] ARCHIVE_STREAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "ARCIVE_STREAM"};
    final String[] LIST_STREAM_PERMISSION = new String[]{"ALL_FUNCTIONS", "LIST_STREAM_PERMISSION"};
    final String[] DELETE_STREAM_PERMISSION = new String[]{"LIST_STREAM_PERMISSION"};

    StreamResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception;

    StreamResponse create(SchoolData data, _Stream entity, AuthenticationResponse authentication) throws Exception;

    StreamResponse delete(SchoolData data, Integer id) throws Exception;

    StreamResponse getById(SchoolData data, Integer Id) throws Exception;

    List<StreamResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception;

    StreamResponse populateResponse(Streams entity);

    StreamResponse update(SchoolData data, _Stream entity, AuthenticationResponse authentication) throws Exception;

}

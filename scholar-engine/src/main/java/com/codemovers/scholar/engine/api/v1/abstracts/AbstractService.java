/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.abstracts;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;

/**
 *
 * @author Mover 11/24/2017
 * @param <T>
 * @param <Z>
 */
public abstract class AbstractService<T, Z> {

    /**
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public Z create(T entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");

    }

    /**
     *
     * @param data
     * @param entity
     * @return
     * @throws Exception
     */
    public Z create(SchoolData data, T entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public Z create(SchoolData data, T entity, AuthenticationResponse authentication) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }


    /**
     *
     * @param Id
     * @return
     * @throws Exception
     */
    public Z getById(Integer Id) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public Z getById(SchoolData data, Integer Id) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    /**
     *
     * @param schoolData
     * @param authentication
     */
    protected void validate(SchoolData schoolData, String authentication) {
        //todo: checkout to see that the user exiss in the db befor creation

    }

    public Z update(SchoolData data, T entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public Z update(SchoolData data, T entity, AuthenticationResponse authentication) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public Z archive(SchoolData data, Integer id) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public Z archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }


    public Z delete(SchoolData data, Integer id) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public List<Z> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public List<Z> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

}

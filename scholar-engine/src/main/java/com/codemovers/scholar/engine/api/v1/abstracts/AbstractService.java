/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.abstracts;

import com.codemovers.scholar.engine.db.entities.SchoolData;

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

    /**
     *
     * @param Id
     * @return
     * @throws Exception
     */
    public Z getById(Integer Id) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    /**
     *
     * @param authentication
     * @param Id
     * @return
     * @throws Exception
     */
    public Z getById(String authentication, Integer Id) throws Exception {
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
}

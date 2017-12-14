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
 */
public abstract class AbstractService<T, Z> {

    public Z create(T entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");

    }

    public Z create(SchoolData data, T entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }


    public Z getById(Integer Id) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }

    public Z getById(String authentication, Integer Id) throws Exception {
        throw new UnsupportedOperationException("Not Supported ");
    }


}

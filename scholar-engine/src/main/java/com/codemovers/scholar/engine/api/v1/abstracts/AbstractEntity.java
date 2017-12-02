/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.abstracts;

import static com.codemovers.scholar.engine.helper.Utilities.validateMandatoryFields;

/**
 *
 * @author Mover 11/20/2017
 */
public abstract class AbstractEntity {

    public AbstractEntity() {
    }

    public void validateMandatory() {
        validateMandatoryFields(this.getClass(), this);
    }

}

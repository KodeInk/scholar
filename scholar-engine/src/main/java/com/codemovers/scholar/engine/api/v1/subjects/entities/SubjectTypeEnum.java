/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.entities;

/**
 *
 * @author mover 7/30/2018
 */
public enum SubjectTypeEnum {
      MAJOR, ELECTIVE;

    public static final SubjectTypeEnum fromString(String text) {
        SubjectTypeEnum returnValue = MAJOR;

        try {
            if (text != null) {
                returnValue = SubjectTypeEnum.valueOf(text.toUpperCase());
            }
        } catch (IllegalArgumentException e) {

            returnValue = MAJOR;
        }

        return returnValue;
    }

    public static final SubjectTypeEnum fromObject(Object text) {
        return fromString(text.toString());
    }
}

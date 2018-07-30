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
public enum SubjectCategoryEnum {
      MAJOR, ELECTIVE;

    public static final SubjectCategoryEnum fromString(String text) {
        SubjectCategoryEnum returnValue = MAJOR;

        try {
            if (text != null) {
                returnValue = SubjectCategoryEnum.valueOf(text.toUpperCase());
            }
        } catch (IllegalArgumentException e) {

            returnValue = MAJOR;
        }

        return returnValue;
    }

    public static final SubjectCategoryEnum fromObject(Object text) {
        return fromString(text.toString());
    }
}

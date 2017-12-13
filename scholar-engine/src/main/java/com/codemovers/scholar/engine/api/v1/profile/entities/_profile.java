/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.profile.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author MOver 12/13/2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class _profile {

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String prefix;
    private String dateOfBirth;
    private String image;
    // private

}

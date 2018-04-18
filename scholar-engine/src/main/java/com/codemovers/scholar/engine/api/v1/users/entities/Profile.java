/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users.entities;

import com.codemovers.scholar.engine.annotation.Mandatory;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.awt.Image;
import java.util.Date;

/**
 *
 * @author mover 4/18/2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {

    private Integer id;
    private @Mandatory
    String firstName;
    private String middleName;
    private @Mandatory
    String lastName;
    private @Mandatory
    String prefix;
    private Date dateOfBirth;
    private Image image;
    private StatusEnum status;
    private Long dateCreated;
    private Integer author;
}

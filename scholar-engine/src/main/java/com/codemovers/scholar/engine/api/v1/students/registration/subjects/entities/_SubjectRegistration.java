/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.registration.subjects.entities;

import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;

/**
 *
 * @author mover 1/3/2018
 */
public class _SubjectRegistration {

    private Integer id;
    private Integer student_term_registration_id;
    private Integer subject_id;
    private Integer paper_id;
    private StatusEnum status;
    private Date date_created;
    private Integer author_id;

}

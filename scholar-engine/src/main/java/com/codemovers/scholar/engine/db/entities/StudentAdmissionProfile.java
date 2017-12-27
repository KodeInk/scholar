/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manny
 */
@Entity
@Table(name = "student_admission_profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentAdmissionProfile.findAll", query = "SELECT s FROM StudentAdmissionProfile s")
    , @NamedQuery(name = "StudentAdmissionProfile.findById", query = "SELECT s FROM StudentAdmissionProfile s WHERE s.id = :id")})
public class StudentAdmissionProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Profile profileId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StudentAdmission studentId;

    public StudentAdmissionProfile() {
    }

    public StudentAdmissionProfile(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
    }

    public StudentAdmission getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentAdmission studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAdmissionProfile)) {
            return false;
        }
        StudentAdmissionProfile other = (StudentAdmissionProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.StudentAdmissionProfile[ id=" + id + " ]";
    }

}

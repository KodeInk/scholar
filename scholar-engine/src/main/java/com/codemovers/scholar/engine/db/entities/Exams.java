/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mover
 */
@Entity
@Table(name = "exams")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exams.findAll", query = "SELECT e FROM Exams e")
    , @NamedQuery(name = "Exams.findById", query = "SELECT e FROM Exams e WHERE e.id = :id")
    , @NamedQuery(name = "Exams.findByName", query = "SELECT e FROM Exams e WHERE e.name = :name")
    , @NamedQuery(name = "Exams.findByContribution", query = "SELECT e FROM Exams e WHERE e.contribution = :contribution")
    , @NamedQuery(name = "Exams.findByStatus", query = "SELECT e FROM Exams e WHERE e.status = :status")})
public class Exams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "%contribution")
    private long contribution;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "examId")
    private Collection<ExamTem> examTemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<Marksheet> marksheetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<ExamTimetable> examTimetableCollection;
    @OneToMany(mappedBy = "examId1")
    private Collection<ExamClass> examClassCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam")
    private Collection<StudentExamRegistration> studentExamRegistrationCollection;

    public Exams() {
    }

    public Exams(Long id) {
        this.id = id;
    }

    public Exams(Long id, String name, long contribution, String status) {
        this.id = id;
        this.name = name;
        this.contribution = contribution;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContribution() {
        return contribution;
    }

    public void setContribution(long contribution) {
        this.contribution = contribution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ExamTem> getExamTemCollection() {
        return examTemCollection;
    }

    public void setExamTemCollection(Collection<ExamTem> examTemCollection) {
        this.examTemCollection = examTemCollection;
    }

    @XmlTransient
    public Collection<Marksheet> getMarksheetCollection() {
        return marksheetCollection;
    }

    public void setMarksheetCollection(Collection<Marksheet> marksheetCollection) {
        this.marksheetCollection = marksheetCollection;
    }

    @XmlTransient
    public Collection<ExamTimetable> getExamTimetableCollection() {
        return examTimetableCollection;
    }

    public void setExamTimetableCollection(Collection<ExamTimetable> examTimetableCollection) {
        this.examTimetableCollection = examTimetableCollection;
    }

    @XmlTransient
    public Collection<ExamClass> getExamClassCollection() {
        return examClassCollection;
    }

    public void setExamClassCollection(Collection<ExamClass> examClassCollection) {
        this.examClassCollection = examClassCollection;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @XmlTransient
    public Collection<StudentExamRegistration> getStudentExamRegistrationCollection() {
        return studentExamRegistrationCollection;
    }

    public void setStudentExamRegistrationCollection(Collection<StudentExamRegistration> studentExamRegistrationCollection) {
        this.studentExamRegistrationCollection = studentExamRegistrationCollection;
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
        if (!(object instanceof Exams)) {
            return false;
        }
        Exams other = (Exams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Exams[ id=" + id + " ]";
    }

}

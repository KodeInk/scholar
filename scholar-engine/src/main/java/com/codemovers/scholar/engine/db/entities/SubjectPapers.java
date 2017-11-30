/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Manny
 */
@Entity
@Table(name = "subject_papers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectPapers.findAll", query = "SELECT s FROM SubjectPapers s")
    , @NamedQuery(name = "SubjectPapers.findById", query = "SELECT s FROM SubjectPapers s WHERE s.id = :id")
    , @NamedQuery(name = "SubjectPapers.findByName", query = "SELECT s FROM SubjectPapers s WHERE s.name = :name")
    , @NamedQuery(name = "SubjectPapers.findByCode", query = "SELECT s FROM SubjectPapers s WHERE s.code = :code")
    , @NamedQuery(name = "SubjectPapers.findByStatus", query = "SELECT s FROM SubjectPapers s WHERE s.status = :status")
    , @NamedQuery(name = "SubjectPapers.findByDateCreated", query = "SELECT s FROM SubjectPapers s WHERE s.dateCreated = :dateCreated")})
public class SubjectPapers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paperId")
    private Collection<TeachingTimetable> teachingTimetableCollection;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne
    private Subjects subjectId;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users authorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paperId")
    private Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paperId")
    private Collection<Marksheet> marksheetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paperId")
    private Collection<ExamTimetable> examTimetableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paperId")
    private Collection<SubjectTeachers> subjectTeachersCollection;

    public SubjectPapers() {
    }

    public SubjectPapers(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @XmlTransient
    public Collection<TeachingTimetable> getTeachingTimetableCollection() {
        return teachingTimetableCollection;
    }

    public void setTeachingTimetableCollection(Collection<TeachingTimetable> teachingTimetableCollection) {
        this.teachingTimetableCollection = teachingTimetableCollection;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }

    public Users getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Users authorId) {
        this.authorId = authorId;
    }

    @XmlTransient
    public Collection<StudentSubjectRegistration> getStudentSubjectRegistrationCollection() {
        return studentSubjectRegistrationCollection;
    }

    public void setStudentSubjectRegistrationCollection(Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection) {
        this.studentSubjectRegistrationCollection = studentSubjectRegistrationCollection;
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
    public Collection<SubjectTeachers> getSubjectTeachersCollection() {
        return subjectTeachersCollection;
    }

    public void setSubjectTeachersCollection(Collection<SubjectTeachers> subjectTeachersCollection) {
        this.subjectTeachersCollection = subjectTeachersCollection;
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
        if (!(object instanceof SubjectPapers)) {
            return false;
        }
        SubjectPapers other = (SubjectPapers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.SubjectPapers[ id=" + id + " ]";
    }

}

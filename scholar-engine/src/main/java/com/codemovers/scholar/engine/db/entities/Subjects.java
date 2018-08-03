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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mover
 */
@Entity
@Table(name = "subjects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subjects.findAll", query = "SELECT s FROM Subjects s")
    , @NamedQuery(name = "Subjects.findById", query = "SELECT s FROM Subjects s WHERE s.id = :id")
    , @NamedQuery(name = "Subjects.findByName", query = "SELECT s FROM Subjects s WHERE s.name = :name")
    , @NamedQuery(name = "Subjects.findByCode", query = "SELECT s FROM Subjects s WHERE s.code = :code")
    , @NamedQuery(name = "Subjects.findByStatus", query = "SELECT s FROM Subjects s WHERE s.status = :status")
    , @NamedQuery(name = "Subjects.findByDateCreated", query = "SELECT s FROM Subjects s WHERE s.dateCreated = :dateCreated")
    , @NamedQuery(name = "Subjects.findSubjectByNameORCode", query = "SELECT s FROM Subjects s WHERE (s.name = :name OR s.code = :code( ")
 , @NamedQuery(name = "Subjects.findSubjectByNameORCodeOnEdit", query = "SELECT s FROM Subjects s WHERE (s.name = :name OR s.code = :code)  AND s.id <> :id ")

})
public class Subjects implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "category")
    private String category;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<SubjectGrading> subjectGradingCollection;
    @OneToMany(mappedBy = "subjectId")
    private Collection<SubjectClass> subjectClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<TeachingTimetable> teachingTimetableCollection;
    @OneToMany(mappedBy = "subjectId")
    private Collection<SubjectPapers> subjectPapersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<SubjectCurriculum> subjectCurriculumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<Marksheet> marksheetCollection;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<ExamTimetable> examTimetableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<SubjectTeachers> subjectTeachersCollection;

    public Subjects() {
    }

    public Subjects(Long id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
    public Collection<SubjectGrading> getSubjectGradingCollection() {
        return subjectGradingCollection;
    }

    public void setSubjectGradingCollection(Collection<SubjectGrading> subjectGradingCollection) {
        this.subjectGradingCollection = subjectGradingCollection;
    }

    @XmlTransient
    public Collection<SubjectClass> getSubjectClassCollection() {
        return subjectClassCollection;
    }

    public void setSubjectClassCollection(Collection<SubjectClass> subjectClassCollection) {
        this.subjectClassCollection = subjectClassCollection;
    }

    @XmlTransient
    public Collection<TeachingTimetable> getTeachingTimetableCollection() {
        return teachingTimetableCollection;
    }

    public void setTeachingTimetableCollection(Collection<TeachingTimetable> teachingTimetableCollection) {
        this.teachingTimetableCollection = teachingTimetableCollection;
    }

    @XmlTransient
    public Collection<SubjectPapers> getSubjectPapersCollection() {
        return subjectPapersCollection;
    }

    public void setSubjectPapersCollection(Collection<SubjectPapers> subjectPapersCollection) {
        this.subjectPapersCollection = subjectPapersCollection;
    }

    @XmlTransient
    public Collection<StudentSubjectRegistration> getStudentSubjectRegistrationCollection() {
        return studentSubjectRegistrationCollection;
    }

    public void setStudentSubjectRegistrationCollection(Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection) {
        this.studentSubjectRegistrationCollection = studentSubjectRegistrationCollection;
    }

    @XmlTransient
    public Collection<SubjectCurriculum> getSubjectCurriculumCollection() {
        return subjectCurriculumCollection;
    }

    public void setSubjectCurriculumCollection(Collection<SubjectCurriculum> subjectCurriculumCollection) {
        this.subjectCurriculumCollection = subjectCurriculumCollection;
    }

    @XmlTransient
    public Collection<Marksheet> getMarksheetCollection() {
        return marksheetCollection;
    }

    public void setMarksheetCollection(Collection<Marksheet> marksheetCollection) {
        this.marksheetCollection = marksheetCollection;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
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
        if (!(object instanceof Subjects)) {
            return false;
        }
        Subjects other = (Subjects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Subjects[ id=" + id + " ]";
    }

}

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
@Table(name = "terms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terms.findAll", query = "SELECT t FROM Terms t")
    , @NamedQuery(name = "Terms.findById", query = "SELECT t FROM Terms t WHERE t.id = :id")
    , @NamedQuery(name = "Terms.findByName", query = "SELECT t FROM Terms t WHERE t.name = :name")
    , @NamedQuery(name = "Terms.findByStartDate", query = "SELECT t FROM Terms t WHERE t.startDate = :startDate")
    , @NamedQuery(name = "Terms.findByEndDate", query = "SELECT t FROM Terms t WHERE t.endDate = :endDate")
    , @NamedQuery(name = "Terms.findByRanking", query = "SELECT t FROM Terms t WHERE t.ranking = :ranking")
    , @NamedQuery(name = "Terms.findByStatus", query = "SELECT t FROM Terms t WHERE t.status = :status")
    , @NamedQuery(name = "Terms.findByDateCreated", query = "SELECT t FROM Terms t WHERE t.dateCreated = :dateCreated")})
public class Terms implements Serializable {

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
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ranking")
    private long ranking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(mappedBy = "termId")
    private Collection<ExamTem> examTemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<TeachingTimetable> teachingTimetableCollection;
    @JoinColumn(name = "study_year_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StudyYear studyYear;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admissionTerm")
    private Collection<StudentAdmission> studentAdmissionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<StudentTermRegistration> studentTermRegistrationCollection;

    public Terms() {
    }

    public Terms(Long id) {
        this.id = id;
    }

    public Terms(Long id, String name, Date startDate, Date endDate, long ranking, String status, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ranking = ranking;
        this.status = status;
        this.dateCreated = dateCreated;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getRanking() {
        return ranking;
    }

    public void setRanking(long ranking) {
        this.ranking = ranking;
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
    public Collection<ExamTem> getExamTemCollection() {
        return examTemCollection;
    }

    public void setExamTemCollection(Collection<ExamTem> examTemCollection) {
        this.examTemCollection = examTemCollection;
    }

    @XmlTransient
    public Collection<TeachingTimetable> getTeachingTimetableCollection() {
        return teachingTimetableCollection;
    }

    public void setTeachingTimetableCollection(Collection<TeachingTimetable> teachingTimetableCollection) {
        this.teachingTimetableCollection = teachingTimetableCollection;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @XmlTransient
    public Collection<StudentAdmission> getStudentAdmissionCollection() {
        return studentAdmissionCollection;
    }

    public void setStudentAdmissionCollection(Collection<StudentAdmission> studentAdmissionCollection) {
        this.studentAdmissionCollection = studentAdmissionCollection;
    }

    @XmlTransient
    public Collection<StudentTermRegistration> getStudentTermRegistrationCollection() {
        return studentTermRegistrationCollection;
    }

    public void setStudentTermRegistrationCollection(Collection<StudentTermRegistration> studentTermRegistrationCollection) {
        this.studentTermRegistrationCollection = studentTermRegistrationCollection;
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
        if (!(object instanceof Terms)) {
            return false;
        }
        Terms other = (Terms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codemovers.scholar.engine.db.entities.Terms[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status")
    , @NamedQuery(name = "Users.findByDateCreated", query = "SELECT u FROM Users u WHERE u.dateCreated = :dateCreated")
    , @NamedQuery(name = "Users.login", query = "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password ")

})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
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
    @ManyToMany(mappedBy = "usersCollection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Roles> rolesCollection;
    //rolesCollection;

    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Roles> UserRoles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<UserRole> userRoleCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<LibraryStock> libraryStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<Classes> classesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<Roles> rolesCollection1;
    @OneToMany(mappedBy = "authorId")
    private Collection<ExamTem> examTemCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<SubjectGrading> subjectGradingCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<ClassStream> classStreamCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<SubjectClass> subjectClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<TeachingTimetable> teachingTimetableCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<SubjectPapers> subjectPapersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<LibraryTransactions> libraryTransactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<Terms> termsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<StudentAdmission> studentAdmissionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<SubjectCurriculum> subjectCurriculumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<CurriculumDetails> curriculumDetailsCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<BookType> bookTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<LibrarySection> librarySectionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<Grading> gradingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<Marksheet> marksheetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<BookCategory> bookCategoryCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<Profile> profileCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<Streams> streamsCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<Subjects> subjectsCollection;
    @OneToMany(mappedBy = "author")
    private Collection<Curriculum> curriculumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherId")
    private Collection<ExamTimetable> examTimetableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<ExamTimetable> examTimetableCollection1;
    @OneToMany(mappedBy = "authorId")
    private Collection<ExamClass> examClassCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<StudyYearCurriculum> studyYearCurriculumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<Books> booksCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<Exams> examsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<GradingDetails> gradingDetailsCollection;
    @OneToMany(mappedBy = "authorId")
    private Collection<StudyYear> studyYearCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherId")
    private Collection<SubjectTeachers> subjectTeachersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<SubjectTeachers> subjectTeachersCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<StudentExamRegistration> studentExamRegistrationCollection;
    @OneToMany(mappedBy = "author")
    private Collection<Contacts> contactsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<LibraryStockInventory> libraryStockInventoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<StudentTermRegistration> studentTermRegistrationCollection;

    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users(Long id, String username, String password, String status, Date dateCreated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

//    @XmlTransient
//    public Collection<Roles> getRolesCollection() {
//        return rolesCollection;
//    }
//
//    public void setRolesCollection(Collection<Roles> rolesCollection) {
//        this.rolesCollection = rolesCollection;
//    }
    @XmlTransient
    public Collection<LibraryStock> getLibraryStockCollection() {
        return libraryStockCollection;
    }

    public void setLibraryStockCollection(Collection<LibraryStock> libraryStockCollection) {
        this.libraryStockCollection = libraryStockCollection;
    }

    @XmlTransient
    public Collection<Classes> getClassesCollection() {
        return classesCollection;
    }

    public void setClassesCollection(Collection<Classes> classesCollection) {
        this.classesCollection = classesCollection;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection1() {
        return rolesCollection1;
    }

    public void setRolesCollection1(Collection<Roles> rolesCollection1) {
        this.rolesCollection1 = rolesCollection1;
    }

    @XmlTransient
    public Collection<ExamTem> getExamTemCollection() {
        return examTemCollection;
    }

    public void setExamTemCollection(Collection<ExamTem> examTemCollection) {
        this.examTemCollection = examTemCollection;
    }

    @XmlTransient
    public Collection<SubjectGrading> getSubjectGradingCollection() {
        return subjectGradingCollection;
    }

    public void setSubjectGradingCollection(Collection<SubjectGrading> subjectGradingCollection) {
        this.subjectGradingCollection = subjectGradingCollection;
    }

    @XmlTransient
    public Collection<ClassStream> getClassStreamCollection() {
        return classStreamCollection;
    }

    public void setClassStreamCollection(Collection<ClassStream> classStreamCollection) {
        this.classStreamCollection = classStreamCollection;
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
    public Collection<LibraryTransactions> getLibraryTransactionsCollection() {
        return libraryTransactionsCollection;
    }

    public void setLibraryTransactionsCollection(Collection<LibraryTransactions> libraryTransactionsCollection) {
        this.libraryTransactionsCollection = libraryTransactionsCollection;
    }

    @XmlTransient
    public Collection<StudentSubjectRegistration> getStudentSubjectRegistrationCollection() {
        return studentSubjectRegistrationCollection;
    }

    public void setStudentSubjectRegistrationCollection(Collection<StudentSubjectRegistration> studentSubjectRegistrationCollection) {
        this.studentSubjectRegistrationCollection = studentSubjectRegistrationCollection;
    }

    @XmlTransient
    public Collection<Terms> getTermsCollection() {
        return termsCollection;
    }

    public void setTermsCollection(Collection<Terms> termsCollection) {
        this.termsCollection = termsCollection;
    }

    @XmlTransient
    public Collection<StudentAdmission> getStudentAdmissionCollection() {
        return studentAdmissionCollection;
    }

    public void setStudentAdmissionCollection(Collection<StudentAdmission> studentAdmissionCollection) {
        this.studentAdmissionCollection = studentAdmissionCollection;
    }

    @XmlTransient
    public Collection<SubjectCurriculum> getSubjectCurriculumCollection() {
        return subjectCurriculumCollection;
    }

    public void setSubjectCurriculumCollection(Collection<SubjectCurriculum> subjectCurriculumCollection) {
        this.subjectCurriculumCollection = subjectCurriculumCollection;
    }

    @XmlTransient
    public Collection<CurriculumDetails> getCurriculumDetailsCollection() {
        return curriculumDetailsCollection;
    }

    public void setCurriculumDetailsCollection(Collection<CurriculumDetails> curriculumDetailsCollection) {
        this.curriculumDetailsCollection = curriculumDetailsCollection;
    }

    @XmlTransient
    public Collection<BookType> getBookTypeCollection() {
        return bookTypeCollection;
    }

    public void setBookTypeCollection(Collection<BookType> bookTypeCollection) {
        this.bookTypeCollection = bookTypeCollection;
    }

    @XmlTransient
    public Collection<LibrarySection> getLibrarySectionCollection() {
        return librarySectionCollection;
    }

    public void setLibrarySectionCollection(Collection<LibrarySection> librarySectionCollection) {
        this.librarySectionCollection = librarySectionCollection;
    }

    @XmlTransient
    public Collection<Grading> getGradingCollection() {
        return gradingCollection;
    }

    public void setGradingCollection(Collection<Grading> gradingCollection) {
        this.gradingCollection = gradingCollection;
    }

    @XmlTransient
    public Collection<Marksheet> getMarksheetCollection() {
        return marksheetCollection;
    }

    public void setMarksheetCollection(Collection<Marksheet> marksheetCollection) {
        this.marksheetCollection = marksheetCollection;
    }

    @XmlTransient
    public Collection<BookCategory> getBookCategoryCollection() {
        return bookCategoryCollection;
    }

    public void setBookCategoryCollection(Collection<BookCategory> bookCategoryCollection) {
        this.bookCategoryCollection = bookCategoryCollection;
    }

    @XmlTransient
    public Collection<Profile> getProfileCollection() {
        return profileCollection;
    }

    public void setProfileCollection(Collection<Profile> profileCollection) {
        this.profileCollection = profileCollection;
    }

    @XmlTransient
    public Collection<Streams> getStreamsCollection() {
        return streamsCollection;
    }

    public void setStreamsCollection(Collection<Streams> streamsCollection) {
        this.streamsCollection = streamsCollection;
    }

    @XmlTransient
    public Collection<Subjects> getSubjectsCollection() {
        return subjectsCollection;
    }

    public void setSubjectsCollection(Collection<Subjects> subjectsCollection) {
        this.subjectsCollection = subjectsCollection;
    }

    @XmlTransient
    public Collection<Curriculum> getCurriculumCollection() {
        return curriculumCollection;
    }

    public void setCurriculumCollection(Collection<Curriculum> curriculumCollection) {
        this.curriculumCollection = curriculumCollection;
    }

    @XmlTransient
    public Collection<ExamTimetable> getExamTimetableCollection() {
        return examTimetableCollection;
    }

    public void setExamTimetableCollection(Collection<ExamTimetable> examTimetableCollection) {
        this.examTimetableCollection = examTimetableCollection;
    }

    @XmlTransient
    public Collection<ExamTimetable> getExamTimetableCollection1() {
        return examTimetableCollection1;
    }

    public void setExamTimetableCollection1(Collection<ExamTimetable> examTimetableCollection1) {
        this.examTimetableCollection1 = examTimetableCollection1;
    }

    @XmlTransient
    public Collection<ExamClass> getExamClassCollection() {
        return examClassCollection;
    }

    public void setExamClassCollection(Collection<ExamClass> examClassCollection) {
        this.examClassCollection = examClassCollection;
    }

    @XmlTransient
    public Collection<StudyYearCurriculum> getStudyYearCurriculumCollection() {
        return studyYearCurriculumCollection;
    }

    public void setStudyYearCurriculumCollection(Collection<StudyYearCurriculum> studyYearCurriculumCollection) {
        this.studyYearCurriculumCollection = studyYearCurriculumCollection;
    }

    @XmlTransient
    public Collection<Books> getBooksCollection() {
        return booksCollection;
    }

    public void setBooksCollection(Collection<Books> booksCollection) {
        this.booksCollection = booksCollection;
    }

    @XmlTransient
    public Collection<Exams> getExamsCollection() {
        return examsCollection;
    }

    public void setExamsCollection(Collection<Exams> examsCollection) {
        this.examsCollection = examsCollection;
    }

    @XmlTransient
    public Collection<GradingDetails> getGradingDetailsCollection() {
        return gradingDetailsCollection;
    }

    public void setGradingDetailsCollection(Collection<GradingDetails> gradingDetailsCollection) {
        this.gradingDetailsCollection = gradingDetailsCollection;
    }

    @XmlTransient
    public Collection<StudyYear> getStudyYearCollection() {
        return studyYearCollection;
    }

    public void setStudyYearCollection(Collection<StudyYear> studyYearCollection) {
        this.studyYearCollection = studyYearCollection;
    }

    @XmlTransient
    public Collection<SubjectTeachers> getSubjectTeachersCollection() {
        return subjectTeachersCollection;
    }

    public void setSubjectTeachersCollection(Collection<SubjectTeachers> subjectTeachersCollection) {
        this.subjectTeachersCollection = subjectTeachersCollection;
    }

    @XmlTransient
    public Collection<SubjectTeachers> getSubjectTeachersCollection1() {
        return subjectTeachersCollection1;
    }

    public void setSubjectTeachersCollection1(Collection<SubjectTeachers> subjectTeachersCollection1) {
        this.subjectTeachersCollection1 = subjectTeachersCollection1;
    }

    @XmlTransient
    public Collection<StudentExamRegistration> getStudentExamRegistrationCollection() {
        return studentExamRegistrationCollection;
    }

    public void setStudentExamRegistrationCollection(Collection<StudentExamRegistration> studentExamRegistrationCollection) {
        this.studentExamRegistrationCollection = studentExamRegistrationCollection;
    }

    @XmlTransient
    public Collection<Contacts> getContactsCollection() {
        return contactsCollection;
    }

    public void setContactsCollection(Collection<Contacts> contactsCollection) {
        this.contactsCollection = contactsCollection;
    }

    @XmlTransient
    public Collection<LibraryStockInventory> getLibraryStockInventoryCollection() {
        return libraryStockInventoryCollection;
    }

    public void setLibraryStockInventoryCollection(Collection<LibraryStockInventory> libraryStockInventoryCollection) {
        this.libraryStockInventoryCollection = libraryStockInventoryCollection;
    }

    @XmlTransient
    public Collection<StudentTermRegistration> getStudentTermRegistrationCollection() {
        return studentTermRegistrationCollection;
    }

    public void setStudentTermRegistrationCollection(Collection<StudentTermRegistration> studentTermRegistrationCollection) {
        this.studentTermRegistrationCollection = studentTermRegistrationCollection;
    }

// set User Roles 
    public Set<Roles> getUserRoles() {
        return UserRoles;
    }

    public void setUserRoles(Set<Roles> UserRoles) {
        this.UserRoles = UserRoles;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Users[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<UserRole> getUserRoleCollection() {
        return userRoleCollection;
    }

    public void setUserRoleCollection(Collection<UserRole> userRoleCollection) {
        this.userRoleCollection = userRoleCollection;
    }

}

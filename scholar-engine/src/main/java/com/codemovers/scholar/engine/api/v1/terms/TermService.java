/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.terms;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.ARCHIVE_CLASS_PERMISSION;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.studyear.StudyYearService;
import static com.codemovers.scholar.engine.api.v1.studyear.StudyYearServiceInterface.LIST_STUDYEAR_PERMISSION;
import com.codemovers.scholar.engine.api.v1.studyear.entities.StudyYearResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities._Term;
import com.codemovers.scholar.engine.db.controllers.StudyYearJpaController;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudyYear;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class TermService extends AbstractService<_Term, TermResponse> implements TermServiceInterface {

    private static final Logger LOG = Logger.getLogger(TermService.class.getName());

    private final TermsJpaController controller;
    private static TermService service = null;

    public TermService() {
        controller = TermsJpaController.getInstance();
    }

    public static TermService getInstance() {
        if (service == null) {
            service = new TermService();
        }
        return service;
    }

    /**
     *
     * @param data
     * @param entity
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public TermResponse create(SchoolData data, _Term entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_TERM_PERMISSION);
        entity.validate();

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: get study Year by Id;
        StudyYear studyYear = StudyYearJpaController.getInstance().findStudyYear(entity.getStudy_year(), data);

        if (studyYear == null) {
            throw new BadRequestException("STUDY YEAR RECORD DOES NOT EXIST");
        }
        Terms term = populateEntity(studyYear, entity);

        //todo: does this term belong to this study year
        Date term_start_date = term.getStartDate();
        Date term_end_date = term.getEndDate();
        Date year_start_date = studyYear.getStartDate();
        Date year_end_date = studyYear.getEndDate();

        validateTermEngine(term_start_date, term_end_date, year_start_date, year_end_date);

        //todo: get all the terms in in this current year, then find if this term overlas another term 
        //todo: get terms from this study year, and then check engine 
        //todo: check to see that there is no term with the same ranking 
        Long termRank = term.getRanking();
        List<Terms> termWithSameRank = controller.findTermByRank(termRank, term.getStudyYear().getId(), data);
        if (termWithSameRank.size() > 0) {
            throw new BadRequestException("A term exists with the same ranking in the same study period ");
        }
        //todo: term rank should not have been used in a given study period 
        //todo: term start date should not be between start and end date of another term in the same study period
        List<Terms> termsWithStartDate = controller.checkTermByStartDate(term.getStartDate(), term.getStudyYear().getId(), data);
        if (termsWithStartDate.size() > 0) {
            throw new BadRequestException("Term start date should not be inside another term duration in the same study period ");
        }

        //todo: term end date should not be between start and end date of another term in the same study period
        List<Terms> termsWithEndDate = controller.checkTermByEndDate(term.getEndDate(), term.getStudyYear().getId(), data);
        if (termsWithEndDate.size() > 0) {
            throw new BadRequestException("Term end date should not be inside another term in the same study period  ");
        }

        //todo: validate term against ranking end date should be in between the start and end of a term :
        term = controller.create(term, data);
        return populateResponse(term);

    }

    /**
     *
     * @param data
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public TermResponse update(SchoolData data, _Term entity, AuthenticationResponse authenticationResponse) throws Exception {
        check_access(UPDATE_TERM_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Terms term = getTerm(entity.getId(), data);
        if (term == null) {
            throw new BadRequestException("Record does not exist");
        }

        StudyYear studyYear = StudyYearJpaController.getInstance().findStudyYear(entity.getStudy_year(), data);

        if (studyYear == null) {
            throw new BadRequestException("STUDY YEAR RECORD DOES NOT EXIST");
        }

        term = populateEntity(entity, term, studyYear);

        //todo: does this term belong to this study year
        Date term_start_date = term.getStartDate();
        Date term_end_date = term.getEndDate();
        Date year_start_date = studyYear.getStartDate();
        Date year_end_date = studyYear.getEndDate();

        validateTermEngine(term_start_date, term_end_date, year_start_date, year_end_date);

        List<Terms> termsWithStartDate = controller.checkTermByStartDate(term.getStartDate(), term.getStudyYear().getId(), term.getId().intValue(), data);
        if (termsWithStartDate.size() > 0) {
            throw new BadRequestException("Term start date should not be inside another term duration in the same study period ");
        }

        //todo: term end date should not be between start and end date of another term in the same study period
        List<Terms> termsWithEndDate = controller.checkTermByEndDate(term.getEndDate(), term.getStudyYear().getId(), term.getId().intValue(), data);
        if (termsWithEndDate.size() > 0) {
            throw new BadRequestException("Term end date should not be inside another term in the same study period  ");
        }

        term = controller.edit(term, data);

        return populateResponse(term);
    }

    /**
     *
     * @param data
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public TermResponse archive(SchoolData data, Integer id) throws Exception {
        check_access(ARCHIVE_TERM_PERMISSION);
        Terms term = getTerm(id, data);

        if (term == null) {
            throw new BadRequestException("Record does not exist");
        }
        term.setStatus(StatusEnum.ARCHIVED.toString());
        term = controller.edit(term, data);
        return populateResponse(term);
    }

    /**
     *
     * @param data
     * @param Id
     * @return
     * @throws Exception
     */
    @Override
    public TermResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_TERM_PERMISSION);
        Terms term = getTerm(Id, data);
        if (term == null) {
            throw new BadRequestException("Record does not exist");
        }

        return populateResponse(term);
    }

    /**
     *
     * @param Id
     * @param data
     * @return
     */
    public Terms getTerm(Integer Id, SchoolData data) {
        Terms term = controller.findTerm(Id, data);
        return term;
    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authenticationResponse
     * @return
     * @throws Exception
     */
    @Override
    public List<TermResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authenticationResponse) throws Exception {

        check_access(LIST_STUDYEAR_PERMISSION);

        List<Terms> list = controller.findTerms(limit, ofset, data);
        List<TermResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((term) -> {
                responses.add(populateResponse(term));
            });
        }

        return responses;

    }

    /**
     *
     * @param data
     * @param studyYear
     * @param ofset
     * @param limit
     * @param authenticationResponse
     * @return
     * @throws Exception
     */
    public List<TermResponse> list(SchoolData data, Integer studyYear, Integer ofset, Integer limit, AuthenticationResponse authenticationResponse) throws Exception {

        check_access(LIST_STUDYEAR_PERMISSION);

        List<Terms> list = controller.findTerms(studyYear, limit, ofset, data);
        List<TermResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((term) -> {
                responses.add(populateResponse(term));
            });
        }

        return responses;

    }

    /**
     *
     * @param data
     * @param query
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<TermResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);

        List<Terms> list = controller.searchTerm(query, limit, ofset, data);

        List<TermResponse> classResponses = new ArrayList<>();
        list.forEach(respond -> {
            classResponses.add(populateResponse(respond));
        });

        return classResponses;

    }

    /**
     *
     * @param term_start_date
     * @param term_end_date
     * @param year_start_date
     * @param year_end_date
     * @throws BadRequestException
     */
    public void validateTermEngine(Date term_start_date, Date term_end_date, Date year_start_date, Date year_end_date) throws BadRequestException {

        if (term_start_date == null || term_end_date == null || year_start_date == null || year_end_date == null) {
            throw new BadRequestException("All Dates are Mandatory");
        }

        if (term_start_date.getTime() >= term_end_date.getTime()) {
            throw new BadRequestException("Term start date can not be greater than or equal to the term end date ");
        }

        if (term_start_date.getTime() < year_start_date.getTime()) {
            throw new BadRequestException("Term start date can not be less than   the year start date :  "+year_start_date.toString());
        }

        if (term_end_date.getTime() > year_end_date.getTime()) {
            throw new BadRequestException("Term end date can not be greater than   the year end date :  "+year_end_date.toString());
        }
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public TermResponse populateResponse(Terms entity) {
        TermResponse response = new TermResponse();
        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setStart_date(entity.getStartDate().getTime());
        response.setEnd_date(entity.getEndDate().getTime());

        if (entity.getStudyYear() != null) {
            StudyYearResponse syr = StudyYearService.getInstance().populateResponse(entity.getStudyYear());
            response.setStudy_year(syr.getTheme());
        }

        Long ranking = entity.getRanking();
        response.setRanking(ranking.intValue());
        response.setStatus((entity.getStatus()));

        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }
        response.setDate_created(entity.getDateCreated().getTime());

        return response;
    }

    /**
     *
     * @param studyYear
     * @param entity
     * @return
     */
    public Terms populateEntity(StudyYear studyYear, _Term entity) {
        //todo: validate period: it should not be between the ranges of the study period:
        Terms term = new Terms();
        term.setStudyYear(studyYear);
        term.setName(entity.getName());
        term.setStartDate(entity.getStart_date());
        term.setEndDate(entity.getEnd_date());
        term.setRanking(entity.getRanking());
        term.setAuthor(new Users(entity.getAuthor_id().longValue()));
        term.setStatus(entity.getStatus().toString());
        term.setDateCreated(new Date());
        return term;
    }

    /**
     *
     * @param entity
     * @param term
     * @param studyYear
     * @return
     */
    public Terms populateEntity(_Term entity, Terms term, StudyYear studyYear) {
        Terms t = term;
        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(term.getName())) {
            t.setName(entity.getName());
        }

        if (term.getStudyYear() != studyYear) {
            t.setStudyYear(studyYear);
        }

        if (entity.getRanking() != null && entity.getRanking() != (term.getRanking())) {
            t.setRanking(entity.getRanking());
        }

        if (entity.getStart_date() != null && entity.getStart_date() != (term.getStartDate())) {
            t.setStartDate(entity.getStart_date());
        }

        if (entity.getEnd_date() != null && entity.getEnd_date() != (term.getEndDate())) {
            t.setEndDate(entity.getEnd_date());
        }
        return t;
    }

}

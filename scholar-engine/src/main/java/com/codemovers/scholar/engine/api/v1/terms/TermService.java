/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.terms;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.studyear.StudyYearServiceInterface.LIST_STUDYEAR_PERMISSION;
import com.codemovers.scholar.engine.api.v1.studyear.entities.StudyYearResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities._Term;
import com.codemovers.scholar.engine.db.controllers.StudyYearJpaController;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
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

        term = controller.create(term, data);
        return populateResponse(term);

    }

    @Override
    public TermResponse update(SchoolData data, _Term entity) throws Exception {
        check_access(UPDATE_TERM_PERMISSION);
        entity.validate();

        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Terms term = controller.findTerm(entity.getId(), data);

        StudyYear studyYear = StudyYearJpaController.getInstance().findStudyYear(entity.getStudy_year(), data);

        if (studyYear == null) {
            throw new BadRequestException("STUDY YEAR RECORD DOES NOT EXIST");
        }

        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(term.getName())) {
            term.setName(entity.getName());
        }

        if (term.getStudyYear() != studyYear) {
            term.setStudyYear(studyYear);
        }

        if (entity.getRanking() != null && entity.getRanking() != (term.getRanking())) {
            term.setRanking(entity.getRanking());
        }

        if (entity.getStart_date() != null && entity.getStart_date() != (term.getStartDate())) {
            term.setStartDate(entity.getStart_date());
        }

        if (entity.getEnd_date() != null && entity.getEnd_date() != (term.getEndDate())) {
            term.setEndDate(entity.getEnd_date());
        }
        term = controller.edit(term, data);

        return populateResponse(term);
    }

    @Override
    public TermResponse archive(SchoolData data, Integer id) throws Exception {
        check_access(ARCHIVE_TERM_PERMISSION);
        Terms term = controller.findTerm(id, data);

        if (term == null) {
            throw new BadRequestException("Record does not exist");
        }
        term.setStatus(StatusEnum.ARCHIVED.toString());
        term = controller.edit(term, data);
        return populateResponse(term);
    }

    @Override
    public TermResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_TERM_PERMISSION);
        Terms term = controller.findTerm(Id, data);
        return populateResponse(term);
    }

    @Override
    public List<TermResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authenticationResponse) throws Exception {

        check_access(LIST_STUDYEAR_PERMISSION);

        List<Terms> list = controller.findTerms(ofset, limit, data);
        List<TermResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((term) -> {
                responses.add(populateResponse(term));
            });
        }

        return responses;

    }

    @Override
    public TermResponse populateResponse(Terms entity) {
        TermResponse response = new TermResponse();
        response.setName(entity.getName());
        response.setStart_date(entity.getStartDate());
        response.setEnd_date(entity.getEndDate());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }
        response.setDate_created(entity.getDateCreated());

        return response;
    }

}

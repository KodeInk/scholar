/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.terms;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class TermService extends AbstractService<_Term, TermResponse> {

    private static final Logger LOG = Logger.getLogger(TermService.class.getName());

    private final TermsJpaController controller;
    private static TermService service = null;

    final String[] CREATE_TERM_PERMISSION = new String[]{"ALL_FUNCTIONS", "CREATE_TERM"};

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
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TermResponse> list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

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

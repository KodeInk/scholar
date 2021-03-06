/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.papers;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import static com.codemovers.scholar.engine.api.v1.classes.ClassServiceInterface.ARCHIVE_CLASS_PERMISSION;
import com.codemovers.scholar.engine.api.v1.classes.entities.ClassResponse;
import com.codemovers.scholar.engine.api.v1.subjects.SubjectService;
import com.codemovers.scholar.engine.api.v1.subjects.entities.SubjectResponse;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPaper;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPapersResponse;
import com.codemovers.scholar.engine.db.controllers.SubjectPapersJpaController;
import com.codemovers.scholar.engine.db.entities.Classes;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.SubjectPapers;
import com.codemovers.scholar.engine.db.entities.Subjects;
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
 * @author mover 1/3/2018
 */
public class SubjectPapersService extends AbstractService<SubjectPaper, SubjectPapersResponse> {

    private static final Logger LOG = Logger.getLogger(SubjectPapersService.class.getName());
    private final SubjectPapersJpaController controller;
    private static SubjectPapersService service = null;

    public SubjectPapersService() {
        this.controller = new SubjectPapersJpaController();
    }

    public static SubjectPapersService getInstance() {
        if (service == null) {
            service = new SubjectPapersService();
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
    public SubjectPapersResponse create(SchoolData data, SubjectPaper entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();
        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: find to see that the subject exists in the database 
        Subjects subject = SubjectService.getInstance().findSubject(entity.getSubject_id(), data);
        SubjectPapers subjectPapers = populateEntity(entity, subject);
        subjectPapers.setDateCreated(new Date());
        verifySubjectPapers(entity, data);
        subjectPapers = controller.create(subjectPapers, data);
        return populateResponse(subjectPapers);
    }

    public void verifySubjectPapers(SubjectPaper entity, SchoolData data) throws BadRequestException {
        //todo: find if there is no paper with the same name or code in the same subject
        List<SubjectPapers> subjectPaperses = controller.findSubjectpapers(entity.getName(), entity.getCode(), entity.getSubject_id(), data);
        if (subjectPaperses.size() > 0) {
            throw new BadRequestException("Subject Paper exists with same name or code");
        }
    }

    @Override
    public SubjectPapersResponse update(SchoolData data, SubjectPaper entity, AuthenticationResponse authentication) throws Exception {
        entity.validate();
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        findSubjectPaper(entity.getId(), data);

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        //todo: find to see that the subject exists in the database 
        Subjects subject = SubjectService.getInstance().findSubject(entity.getSubject_id(), data);
        SubjectPapers subjectPapers = populateEntity(entity, subject);
        subjectPapers.setDateCreated(new Date());
        verifySubjectPapers(entity, data);
        subjectPapers = controller.create(subjectPapers, data);
        return populateResponse(subjectPapers);
    }

    public void findSubjectPaper(Integer id, SchoolData data) throws BadRequestException {
        SubjectPapers subjectpaper = controller.findSubjectPaper(id, data);

        if (subjectpaper == null) {
            throw new BadRequestException("SUBJECT  RECORD DOES NOT EXIST");
        }
    }

    @Override
    public SubjectPapersResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        return super.archive(data, id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param data
     * @param ofset
     * @param limit
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public List<SubjectPapersResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        List<SubjectPapers> list = controller.findSubjectPapers(limit, ofset, data);
        List<SubjectPapersResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((subject) -> {
                responses.add(populateResponse(subject));
            });
        }

        return responses;
    }

    @Override
    public SubjectPapersResponse getById(SchoolData data, Integer Id, AuthenticationResponse authentication) throws Exception {
        return super.getById(data, Id, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SubjectPapersResponse> search(SchoolData data, String query, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_CLASS_PERMISSION);

        List<SubjectPapers> list = controller.query(query, limit, ofset, data);

        List<SubjectPapersResponse> subjectpaperresponse = new ArrayList<>();
        list.forEach(respond -> {
            subjectpaperresponse.add(populateResponse(respond));
        });

        return subjectpaperresponse;

    }

    //todo: populate entity 
    public SubjectPapers populateEntity(SubjectPaper entity, Subjects subject) {
        //todo: populate Entity
        SubjectPapers subjectPapers = new SubjectPapers();
        subjectPapers.setCode(entity.getCode());
        subjectPapers.setName(entity.getName());
        subjectPapers.setSubject(subject);
        subjectPapers.setStatus(entity.getStatus().name());
        subjectPapers.setAuthor(new Users(entity.getAuthor_id().longValue()));
        return subjectPapers;
    }

    public SubjectPapersResponse populateResponse(SubjectPapers entity) {
        SubjectPapersResponse response = new SubjectPapersResponse();

        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setStatus(entity.getStatus());
        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }

        if (entity.getDateCreated() != null) {
            response.setDate_created(entity.getDateCreated().getTime());
        }

        SubjectResponse subjectResponse = SubjectService.getInstance().populateResponse(entity.getSubject());
        response.setSubject(subjectResponse);

        return response;
    }

}

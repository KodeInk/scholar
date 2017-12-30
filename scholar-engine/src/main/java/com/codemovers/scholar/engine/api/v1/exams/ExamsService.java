/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.exams;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities.ExamResponse;
import com.codemovers.scholar.engine.api.v1.exams.entities._Exam;
import com.codemovers.scholar.engine.db.controllers.ExamsJpaController;
import com.codemovers.scholar.engine.db.entities.Exams;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.check_access;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mover 12/30/2017
 */
public class ExamsService extends AbstractService<_Exam, ExamResponse> implements ExamsServiceInterface {

    private final ExamsJpaController controller;
    private static ExamsService service = null;


    public ExamsService() {
        controller = ExamsJpaController.getInstance();
    }

    public static ExamsService getInstance() {
        if (service == null) {
            service = new ExamsService();
        }
        return service;
    }

    @Override
    public ExamResponse create(SchoolData data, _Exam entity, AuthenticationResponse authentication) throws Exception {
        check_access(CREATE_EXAM_PERMISSION);
        entity.validate();

        entity.setAuthor_id(authentication.getId());
        entity.setStatus(StatusEnum.ACTIVE);

        Exams exam = new Exams();
        exam.setName(entity.getName());
        exam.setContribution(entity.getContribution().longValue());
        exam.setStatus(entity.getStatus().toString());
        exam.setAuthor(new Users(authentication.getId().longValue()));

        exam = controller.create(exam, data);

        return populateResponse(exam);

    }

    @Override
    public ExamResponse update(SchoolData data, _Exam entity, AuthenticationResponse authentication) throws Exception {
        check_access(UPDATE_EXAM_PERMISSION);
        entity.validate();

        //todo: get the entity by id if exists
        if (entity.getId() == null) {
            throw new BadRequestException("UNIQUE ID MISSING");
        }

        Exams exam = controller.findExam(entity.getId(), data);

        if (entity.getName() != null && !entity.getName().equalsIgnoreCase(exam.getName())) {
            exam.setName(entity.getName());
        }

        Long contribution = exam.getContribution();

        if (entity.getContribution() != null && entity.getContribution() != (contribution.intValue())) {
            exam.setContribution(entity.getContribution().longValue());
        }

        exam = controller.edit(exam, data);
        return populateResponse(exam);
    }

    @Override
    public ExamResponse archive(SchoolData data, Integer id, AuthenticationResponse authentication) throws Exception {
        check_access(ARCHIVE_EXAM_PERMISSION);
        Exams exam = controller.findExam(id, data);

        if (exam == null) {
            throw new BadRequestException("Record does not exist");
        }
        exam.setStatus(StatusEnum.ARCHIVED.toString());
        exam = controller.edit(exam, data);
        return populateResponse(exam);

    }

    @Override
    public List<ExamResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {
        check_access(LIST_EXAM_PERMISSION);

        List<Exams> list = controller.findExamEntities(ofset, limit, data);
        List<ExamResponse> responses = new ArrayList<>();
        if (list != null) {
            list.forEach((_exam) -> {
                responses.add(populateResponse(_exam));
            });
        }
        return responses;

    }

    @Override
    public ExamResponse getById(SchoolData data, Integer Id) throws Exception {
        check_access(LIST_EXAM_PERMISSION);
        Exams exam = controller.findExam(Id, data);
        return populateResponse(exam);

    }

    @Override
    public ExamResponse populateResponse(Exams entity) {

        ExamResponse response = new ExamResponse();
        response.setId(entity.getId().intValue());
        response.setName(entity.getName());
        Long contribution = entity.getContribution();
        response.setContribution(contribution.intValue());
        response.setStatus(entity.getStatus());

        if (entity.getAuthor() != null) {
            response.setAuthor(entity.getAuthor().getUsername());
        }


        return response;

    }

}

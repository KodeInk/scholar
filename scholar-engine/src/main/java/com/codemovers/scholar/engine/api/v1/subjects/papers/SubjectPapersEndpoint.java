/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.subjects.papers;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.subjects.SubjectEndpoint;
import com.codemovers.scholar.engine.api.v1.subjects.SubjectService;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities.SubjectPapersResponse;
import com.codemovers.scholar.engine.api.v1.subjects.papers.entities._SubjectPapers;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.Collection;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover 1/3/2018
 */
@Path("/")
public class SubjectPapersEndpoint extends AbstractEndpoint<_SubjectPapers, SubjectPapersResponse> {

    private static final Logger LOG = Logger.getLogger(SubjectPapersEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    SubjectPapersService service = null;

    public SubjectPapersEndpoint() {
        service = new SubjectPapersService();
    }

    @Override
    public void validateAuthentication(SchoolData schoolData, String authentication) {
        super.validateAuthentication(schoolData, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPapersResponse create(_SubjectPapers entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPapersResponse update(_SubjectPapers entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectPapersResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<SubjectPapersResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }




}

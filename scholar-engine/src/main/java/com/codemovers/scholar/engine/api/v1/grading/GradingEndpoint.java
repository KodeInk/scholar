/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.grading.entities.GradingResponse;
import com.codemovers.scholar.engine.api.v1.grading.entities._Grading;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.Collection;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover 12/20/2017
 */
public class GradingEndpoint extends AbstractEndpoint<_Grading, GradingResponse> {

    private static final Logger LOG = Logger.getLogger(GradingEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    private GradingService service = null;

    /**
     *
     */
    public GradingEndpoint() {
        service = new GradingService();
    }

    @Override
    public void validateAuthentication(SchoolData schoolData, String authentication) {
        super.validateAuthentication(schoolData, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingResponse create(_Grading entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingResponse update(_Grading entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<GradingResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

}

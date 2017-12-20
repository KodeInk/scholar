/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.admissions;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.admissions.entities.AdmissionResponse;
import com.codemovers.scholar.engine.api.v1.admissions.entities._Admission;
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
public class AdmissionEndpoint extends AbstractEndpoint<_Admission, AdmissionResponse> {

    private static final Logger LOG = Logger.getLogger(AdmissionEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    AdmissionService service = null;

    public AdmissionEndpoint() {
        service = new AdmissionService();
    }

    @Override
    public void validateAuthentication(SchoolData schoolData, String authentication) {
        super.validateAuthentication(schoolData, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmissionResponse create(_Admission entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmissionResponse update(_Admission entity, String authentication, HttpServletRequest httpRequest) {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<AdmissionResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response archive(Integer id, String authentication, HttpServletRequest httpRequest) {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }


}

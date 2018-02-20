/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.marksheet;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.marksheet.entities.MarkSheetResponse;
import com.codemovers.scholar.engine.api.v1.marksheet.entities._MarkSheet;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author mover 12/20/2017
 */
public class MarkSheetEndpoint extends AbstractEndpoint<_MarkSheet, MarkSheetResponse> {

    private static final Logger LOG = Logger.getLogger(MarkSheetEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    private MarkSheetService service = null;

    /**
     *
     */
    public MarkSheetEndpoint() {
        service = new MarkSheetService();
    }

    @Override
    public void validateAuthentication(SchoolData schoolData, String authentication) {
        super.validateAuthentication(schoolData, authentication); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarkSheetResponse create(_MarkSheet entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarkSheetResponse update(_MarkSheet entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarkSheetResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MarkSheetResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.grading.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.grading.details.entities.GradingDetail;
import com.codemovers.scholar.engine.api.v1.grading.details.entities.GradingDetailResponse;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover 8/19/2018
 */
public class GradingDetailsEndpoint extends AbstractEndpoint<GradingDetail, GradingDetailResponse> {

    private static final Logger LOG = Logger.getLogger(GradingDetailsEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    private GradingDetailsService service = null;
    private AuthenticationResponse authentication = null;

    public GradingDetailsEndpoint() {
        service = new GradingDetailsService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public GradingDetailResponse create(GradingDetail entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingDetailResponse update(GradingDetail entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GradingDetailResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingDetailResponse get(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.get(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GradingDetailResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}

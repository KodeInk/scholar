/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.curriculum.details;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.classes.ClassEndpoint;
import com.codemovers.scholar.engine.api.v1.classes.ClassService;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities.CurriculumDetailResponse;
import com.codemovers.scholar.engine.api.v1.curriculum.details.entities._CurriculumDetail;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.Collection;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover 12/28/2017
 */
public class CurriculumDetailEndpoint extends AbstractEndpoint<_CurriculumDetail, CurriculumDetailResponse> {

    private static final Logger LOG = Logger.getLogger(CurriculumDetailEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;

    private CurriculumDetailService service = null;
    private AuthenticationResponse authentication = null;

    /**
     *
     */
    public CurriculumDetailEndpoint() {
        service = new CurriculumDetailService();
    }

    /**
     *
     * @param schoolData
     * @param authentication
     * @throws Exception
     */
    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public CurriculumDetailResponse create(_CurriculumDetail entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.create(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumDetailResponse update(_CurriculumDetail entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.update(entity, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CurriculumDetailResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.archive(id, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<CurriculumDetailResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        return super.list(start, end, authentication, httpRequest); //To change body of generated methods, choose Tools | Templates.
    }



}

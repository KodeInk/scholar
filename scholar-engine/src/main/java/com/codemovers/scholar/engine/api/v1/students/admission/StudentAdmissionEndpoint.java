/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.students.admission;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEndpoint;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities.StudentAdmissionResponse;
import com.codemovers.scholar.engine.api.v1.students.admission.entities._StudentAdmission;
import com.codemovers.scholar.engine.api.v1.users.UserService;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import static com.codemovers.scholar.engine.helper.Utilities.tenantdata;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author mover 12/30/2017
 */
@Path("/")
public class StudentAdmissionEndpoint extends AbstractEndpoint<_StudentAdmission, StudentAdmissionResponse> {

    private static final Logger LOG = Logger.getLogger(StudentAdmissionEndpoint.class.getName());
    @Context
    private ContainerRequestContext context;
    StudentAdmissionService service = null;
    private AuthenticationResponse authentication = null;

    public StudentAdmissionEndpoint() {
        service = new StudentAdmissionService();
    }

    @Override
    public void validate(SchoolData schoolData, String authentication) throws Exception {
        this.authentication = UserService.getInstance().validateAuthentication(schoolData, authentication);
    }

    @Override
    public StudentAdmissionResponse create(_StudentAdmission entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.update(tenantdata, entity, this.authentication);
    }

    @Override
    public StudentAdmissionResponse update(_StudentAdmission entity, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.update(tenantdata, entity, this.authentication);
    }

    @Override
    public StudentAdmissionResponse archive(Integer id, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.archive(tenantdata, id, this.authentication);
    }

    @Override
    public List<StudentAdmissionResponse> list(int start, int end, String authentication, HttpServletRequest httpRequest) throws Exception {
        validate(tenantdata, authentication);
        return service.list(tenantdata, start, end, this.authentication);
    }




}

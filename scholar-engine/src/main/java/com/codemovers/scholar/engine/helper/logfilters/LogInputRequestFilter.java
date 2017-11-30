package com.codemovers.scholar.engine.helper.logfilters;

import com.codemovers.scholar.engine.helper.Utilities;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.server.ContainerRequest;

@Priority(100)
public class LogInputRequestFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(LogInputRequestFilter.class.getName());

    @Context private UriInfo uriInfo;
    
    @Override
    public void filter(ContainerRequestContext requestContext) {
        LOG.log(Level.INFO, "------------------------------ session start -----------------------------------");
        String logId = Utilities.getLogId();

        try {
            String userAgent = requestContext.getHeaderString("User-Agent");
            if (userAgent != null && userAgent.contains("ELB-HealthChecker")) {
                return;
            }

            if (requestContext.getHeaderString("tenantId") != null) {
                logId = requestContext.getHeaderString("tenantId") + "_" + logId;
            } else if (requestContext.getHeaderString("token") != null) {
                // hint: no authentication done here !!

            } else {
                logId = "unknownTenant_" + logId;
            }

            requestContext.setProperty("logId", logId);
            String logString = logId + " ::";
            
            ContainerRequest request = (ContainerRequest) requestContext;
            
            logString += "\n\tPath=" + uriInfo.getAbsolutePath();
            logString += "\n\tMethod=" + request.getMethod();

            if (!requestContext.getHeaders().isEmpty()) {
                logString += "\n\tHeaders=" + requestContext.getHeaders();
            }
            if (!uriInfo.getPathParameters().isEmpty()) {
                logString += "\n\tPathParameters=" + uriInfo.getPathParameters().toString();
            }
            if (!uriInfo.getQueryParameters().isEmpty()) {
                logString += "\n\tQueryParameters=" + uriInfo.getQueryParameters().toString();
            }

            if (requestContext.hasEntity() && request.getLength() > 2) {
                request.bufferEntity();
                logString += "\n\tbody=" + Utilities.readAsString(request.getEntityStream());
            } else {
                logString += "\n\tBody=<none>";
            }
            LOG.log(Level.INFO, logString);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "unexpected exception\n{0}", new Object[]{Utilities.getStackTrace(e)});
        }
        
    }
    
}

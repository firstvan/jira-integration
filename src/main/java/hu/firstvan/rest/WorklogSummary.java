package hu.firstvan.rest;

import hu.firstvan.controller.IssueBean;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/worklogsummary")
public class WorklogSummary {

    @Inject
    private IssueBean issueBean;

    @GET
    @Path("/{username}/{date}")
    public Response handleGETRequest(@PathParam("username") String username, @PathParam("date") String date,
                                     @HeaderParam("x-username") String user, @HeaderParam("x-token") String token) {

        String result = issueBean.getWorklogSummaryByDateAndUser(username, date, user, token);

        if (result.startsWith("!")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        } else {
            return Response.ok(result).build();
        }
    }
}

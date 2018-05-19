package hu.firstvan.rest;

import hu.firstvan.communication.LoggedUser;
import hu.firstvan.communication.TokenHolder;
import hu.firstvan.controller.LoginBean;
import org.primefaces.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/restlogin")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LoginRest {

    @Inject
    private LoginBean login;


    @POST
    public Response handlePOSTRequest(final String payload) {
        JSONObject obj = new JSONObject(payload);
        String username = obj.getString("username");
        String password = obj.getString("password");

        boolean succ = login.doLogin(username, password, true);

        LoggedUser loggedUser = LoggedUser.getInstance();

        TokenHolder holder = loggedUser.getRestUserToken(username);

        if (succ) {
            return Response.ok(holder)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Hibás azonosítás!").build();
        }
    }
}

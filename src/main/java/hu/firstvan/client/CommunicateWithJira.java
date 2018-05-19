package hu.firstvan.client;

import hu.firstvan.communication.LoggedUser;
import hu.firstvan.communication.TokenHolder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CommunicateWithJira {
    protected final String BASE_URL = "http://localhost:8080/";

    protected Response get(final String path, final String username) {
        LoggedUser loggedUser = LoggedUser.getInstance();
        TokenHolder tokenHolder = loggedUser.getUserToken(username);

        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + path).request()
                .accept(MediaType.APPLICATION_JSON_TYPE).cookie("JSESSIONID", tokenHolder.getToken())
                .header("x-ausername", tokenHolder.getUsername());

        return request.get();
    }

    protected <T> T getObjectFromPath(Class<T> clazz, final String path, final String username) {
        try (Response response = this.get(path, username)) {
            if (response.getStatus() == 200) {
                return response.readEntity(clazz);
            }
        }

        return null;
    }
}

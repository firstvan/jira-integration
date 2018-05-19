package hu.firstvan.client;

import hu.firstvan.communication.LoggedUser;
import hu.firstvan.communication.TokenHolder;
import hu.firstvan.model.User;
import jdk.nashorn.internal.parser.Token;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

@Stateless
public class LoginToJira extends CommunicateWithJira {

    public boolean login(final User user, final boolean isRest) {
        LoggedUser loggedUser = LoggedUser.getInstance();

        Client client = ClientBuilder.newClient();
        Entity<User> userEntity = Entity.json(user);
        Response resp = client.target(BASE_URL + "rest/auth/1/session")
                            .request(MediaType.APPLICATION_JSON_TYPE).buildPost(userEntity).invoke();

        if (resp.getStatus() != 200) {
            return false;
        }

        Optional<NewCookie> optCookie = resp.getCookies().entrySet().stream()
                .filter(entry -> entry.getKey().equals("JSESSIONID")).map(Map.Entry::getValue).findFirst();

        TokenHolder tokenHolder = new TokenHolder();

        optCookie.ifPresent(newCookie -> {
            tokenHolder.setToken(newCookie.getValue());
            tokenHolder.setUsername(user.getUsername());
            if (!isRest) {
                loggedUser.addToken(tokenHolder);
            } else {
                loggedUser.addRestToken(tokenHolder);
            }
        });

        return tokenHolder.isTokenAvailable();
    }
}

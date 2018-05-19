package hu.firstvan.communication;

import java.util.HashMap;
import java.util.Map;

public class LoggedUser {

    private static LoggedUser loggedUser;

    private Map<String, TokenHolder> users;
    private Map<String, TokenHolder> restUsers;

    private LoggedUser() {
        users = new HashMap<>();
        restUsers = new HashMap<>();
    }

    public static LoggedUser getInstance() {
        if (loggedUser == null) {
            loggedUser = new LoggedUser();
        }

        return loggedUser;
    }

    public void addUser(final String username, final String token) {
        users.put(username, new TokenHolder(username, token));
    }

    public void addToken(final TokenHolder token) {
        users.put(token.getUsername(), token);
    }

    public TokenHolder getUserToken(final String username) {
        return users.get(username);
    }

    public boolean userIsAuthenticated(final String user) {
        return users.keySet().stream().anyMatch(key -> key.equals(user));
    }

    public void addRestUser(final String username, final String token) {
        restUsers.put(username, new TokenHolder(username, token));
    }

    public void addRestToken(final TokenHolder token) {
        restUsers.put(token.getUsername(), token);
    }

    public TokenHolder getRestUserToken(final String username) {
        return restUsers.get(username);
    }

    public boolean restUserIsAuthenticated(final String user) {
        return restUsers.keySet().stream().anyMatch(key -> key.equals(user));
    }
}

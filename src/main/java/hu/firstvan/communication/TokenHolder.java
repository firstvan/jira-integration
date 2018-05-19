package hu.firstvan.communication;

import org.apache.commons.lang3.StringUtils;


public class TokenHolder {
    private String username;
    private String token;

    public TokenHolder() {
    }

    public TokenHolder(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isTokenAvailable() {
        return StringUtils.isNotEmpty(this.token) && StringUtils.isNotEmpty(this.username);
    }
}

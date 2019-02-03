package io.reativ.samples.springauthenticationtoken.security.models.responses;

import io.reativ.samples.springauthenticationtoken.core.http.responses.AbstractJsonResponse;
import io.reativ.samples.springauthenticationtoken.security.models.entities.User;

import java.util.Date;

public class LoginResponse extends AbstractJsonResponse {

    private String token;
    private String refreshToken;
    private Date expire;
    private Long userId;
    private String username;
    private Date lastLogin;


    public LoginResponse() {
    }

    public LoginResponse(User user, String token) {
        this();
        this.token = token;
        this.userId = user.getId();
        this.username = user.getUsername();
        this.lastLogin = user.getLastLogin();
    }

    public LoginResponse(User user, String token, String refreshToken) {
        this(user, token);
        this.refreshToken = refreshToken;
    }

    public LoginResponse(User user, String token, String refreshToken, Date expire) {
        this(user, token, refreshToken);
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

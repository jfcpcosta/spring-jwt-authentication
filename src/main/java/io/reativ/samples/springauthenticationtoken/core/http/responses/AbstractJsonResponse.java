package io.reativ.samples.springauthenticationtoken.core.http.responses;

import com.google.gson.Gson;

import java.util.Date;

public abstract class AbstractJsonResponse {

    private Long timestamp;
    private Integer status;
    private String message;
    private String path;

    public AbstractJsonResponse() {
        this.status = 200;
        this.timestamp = new Date().getTime();
    }

    public AbstractJsonResponse(Integer status, String message) {
        this();
        this.message = message;
        this.status = status;
    }

    public AbstractJsonResponse(String message) {
        this(200, message);
    }

    public String asJson() {
        return new Gson().toJson(this);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

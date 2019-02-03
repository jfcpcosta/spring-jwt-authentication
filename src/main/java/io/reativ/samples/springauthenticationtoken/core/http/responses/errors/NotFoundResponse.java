package io.reativ.samples.springauthenticationtoken.core.http.responses.errors;

public class NotFoundResponse extends AbstractErrorResponse {

    public NotFoundResponse(String message) {
        super(404, "Not Found", message);
    }
}

package io.reativ.samples.springauthenticationtoken.core.http.responses.errors;

public class UnauthorizedResponse extends AbstractErrorResponse {

    public UnauthorizedResponse(String message) {
        super(401, "Unauthorized", message);
    }
}

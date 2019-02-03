package io.reativ.samples.springauthenticationtoken.core.http.responses.errors;

public class ForbiddenResponse extends AbstractErrorResponse {

    public ForbiddenResponse(String message) {
        super(403, "Forbidden", message);
    }
}

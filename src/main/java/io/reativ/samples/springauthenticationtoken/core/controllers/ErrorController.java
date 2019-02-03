package io.reativ.samples.springauthenticationtoken.core.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {

    @RequestMapping(name = "error", method = RequestMethod.GET)
    public String error(HttpServletRequest httpRequest) {
        return "error";
    }
}

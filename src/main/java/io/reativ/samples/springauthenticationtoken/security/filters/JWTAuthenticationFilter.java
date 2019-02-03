package io.reativ.samples.springauthenticationtoken.security.filters;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.reativ.samples.springauthenticationtoken.core.http.responses.AbstractJsonResponse;
import io.reativ.samples.springauthenticationtoken.core.http.responses.errors.UnauthorizedResponse;
import io.reativ.samples.springauthenticationtoken.security.models.entities.User;
import io.reativ.samples.springauthenticationtoken.security.models.responses.LoginResponse;
import io.reativ.samples.springauthenticationtoken.security.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static io.reativ.samples.springauthenticationtoken.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UsersRepository usersRepository;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = ctx.getBean(UsersRepository.class);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx, String loginEndpoint) {
        this(authenticationManager, ctx);
        this.setFilterProcessesUrl(loginEndpoint);
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword(),
                            Collections.emptyList()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();
        Date expire = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expire)
                .sign(HMAC512(SECRET.getBytes()));

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // TODO: Create refresh token

        flushResponse(response, new LoginResponse(user, token, "", expire));

        user.setLastLogin(new Date());
        usersRepository.save(user);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        flushResponse(response, new UnauthorizedResponse(failed.getMessage()));
    }

    private void flushResponse(HttpServletResponse response, AbstractJsonResponse jsonResponse) throws IOException {
        response.getWriter().write(jsonResponse.asJson());
        response.getWriter().flush();
        response.getWriter().close();
    }
}

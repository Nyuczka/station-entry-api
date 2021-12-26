package com.exercise.stationentryapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exercise.stationentryapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Value("stationAPI.security.jwt.secret")
    private String authenticationSecret;

    @Value("stationAPI.security.expiration.interval")
    private long interval;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername()
                    , user.getPassword()));
        } catch (final BadCredentialsException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetails user = (UserDetails) authResult.getPrincipal();
        LocalDateTime localDateTime = LocalDateTime.now().plus(interval, ChronoUnit.MILLIS);
        String generatedToken = JWT.create().
                withSubject(user.getUsername()).
                withExpiresAt(Date.from(
                        localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC256(authenticationSecret));
        response.addHeader("Authorization", "Bearer " + generatedToken);
        super.successfulAuthentication(request, response, chain, authResult);
    }
}

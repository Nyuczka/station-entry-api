package com.exercise.stationentryapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exercise.stationentryapi.model.dto.User;
import com.exercise.stationentryapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String AUTH_SECRET = "TjWnZr4u7x!z%C*F-JaNdRgUkXp2s5v8";

    private final Long INTERVAL = 1800000L;

    private final AuthenticationManager authenticationManager;

    private UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername()
                    , user.getPassword(), getAuthoritiesForUser(user)));
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
        LocalDateTime localDateTime = LocalDateTime.now().plus(INTERVAL, ChronoUnit.MILLIS);
        String generatedToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Date.from(
                        localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(AUTH_SECRET));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", "Bearer " + generatedToken);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().write(jsonObject.toJSONString());
        response.getWriter().flush();
    }

    private List<GrantedAuthority> getAuthoritiesForUser(User user){
        return userService.getAuthoritiesForUser(user);
    }
}

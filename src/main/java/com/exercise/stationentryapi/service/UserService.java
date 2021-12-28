package com.exercise.stationentryapi.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private static final List<User> USERS =
            Arrays.asList(new User("user", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))),
                    new User("admin", "nimda", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = USERS.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
        if (user != null) {
            return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getAuthorities());
        }
        throw new UsernameNotFoundException("User with given name doesn't exist.");
    }

    public List<GrantedAuthority> getAuthoritiesForUser(com.exercise.stationentryapi.model.dto.User user) {
        User returnedUser = USERS.stream().filter(u ->
                (u.getUsername().equalsIgnoreCase(user.getUsername()) &&
                        u.getPassword().equalsIgnoreCase(user.getPassword()))).findFirst().orElse(null);
        if(returnedUser != null){
            return new ArrayList<>(returnedUser.getAuthorities());
        }

        return Collections.EMPTY_LIST;
    }
}

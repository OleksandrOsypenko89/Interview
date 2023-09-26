package com.osypenko.services.user;

import com.osypenko.model.users.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional= userService.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Unknown user: " + email);
        }
        User user = userOptional.get();
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
        session.setAttribute("user", user);
        return userDetails;
    }
}

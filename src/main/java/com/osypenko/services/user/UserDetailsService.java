package com.osypenko.services.user;

import com.osypenko.model.users.User;
import com.osypenko.services.statistics.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.osypenko.constant.NameLogs.UNKNOWN_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserService userService;
    private final StatisticService statisticService;

    public User getUser(UserDetails userDetails) {
        Optional<User> userOptional = userService.findByEmail(userDetails.getUsername());
        return userOptional.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(UNKNOWN_USER + email);
        }
        User user = userOptional.get();
        statisticService.deletionOfOutdatedStatistics(user);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }
}
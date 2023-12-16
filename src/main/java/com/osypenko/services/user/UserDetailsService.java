package com.osypenko.services.user;

import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.NameLogs.USER_LOGIN;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserService userService;
    private final MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email).orElseThrow();
        if (user.getEmail().equals(DEMO_GMAIL_COM)) {
            mailService.sendSimpleMessage(OLEKSANDR_GMAIL_COM, FIXED_LOGIN_ACCOUNT_FOR_RECRUITERS);
        }
        log.info(USER_LOGIN, user.getEmail());
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }
}
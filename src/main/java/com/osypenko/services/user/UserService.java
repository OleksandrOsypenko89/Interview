package com.osypenko.services.user;

import com.osypenko.config.SecurityConfig;
import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameLogs.*;
import static com.osypenko.constant.NameLogs.DELETE_USER;
import static com.osypenko.constant.NameSessionAttributes.NEW_PASSWORD_FLAG;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession session;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
        log.info(DELETE_USER, user.getEmail());
    }

    public void saveAndFlushUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public User fromUserDetailsToUser(UserDetails userDetails) {
        return findByEmail(userDetails.getUsername()).orElseThrow();
    }

    public void updateDate(User user, String firstName, String lastName, String email) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        saveAndFlushUser(user);
        log.info(UPDATE_DATA_USER, user.getEmail());
    }

    public User createNewUser(User user, String firstName, String lastName, String email, String password) {
        passwordEncoding(password, user);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
        user.setListQuestionInterviews(new HashSet<>());
        user.setListQuestionTesting(new HashSet<>());
        user.setListStudyQuestion(new HashSet<>());
        user.setStatistic(new HashSet<>());
        log.info(REGISTRATION_NEW_USER, user.getEmail());
        return user;
    }

    public void passwordEncoding(String password, User user) {
        String encode = SecurityConfig.PASSWORD_ENCODER.encode(password);
        user.setPassword(encode);
    }

    public String userPasswordChange(String email, String passwordOne, String passwordTwo) {
        if (passwordOne.equals(passwordTwo)) {
            User user = findByEmail(email).orElseThrow();
            passwordEncoding(passwordOne, user);
            saveAndFlushUser(user);
            log.info(USER_UPDATE_PASSWORD, user.getEmail());
            return REDIRECT + LOGIN;
        }
        session.setAttribute(NEW_PASSWORD_FLAG, false);
        return REDIRECT + NEW_PASSWORD;
    }
}
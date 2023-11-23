package com.osypenko.services.user;

import com.osypenko.config.SecurityConfig;
import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.osypenko.constant.Endpoints.LOGIN;
import static com.osypenko.constant.Endpoints.REDIRECT;
import static com.osypenko.constant.NameLogs.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(User user) {
        log.info(DELETE_USER, user.getEmail());
        userRepository.delete(user);
    }

    public void flushUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public User getUser(UserDetails userDetails) {
        Optional<User> userOptional = findByEmail(userDetails.getUsername());
        return userOptional.orElse(null);
    }

    public void updateDate(User user, String firstName, String lastName, String email) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        flushUser(user);
        log.info(UPDATE_DATA_USER, user.getEmail());
    }

    public void createNewUser(User user, String firstName, String lastName, String email, String password) {
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
    }

    public void passwordEncoding(String password, User user) {
        String encode = SecurityConfig.PASSWORD_ENCODER.encode(password);
        user.setPassword(encode);
    }

    public String userPasswordChange(String email, String passwordOne, String passwordTwo) {
        if (passwordOne.equals(passwordTwo)) {
            Optional<User> optionalUser = findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                passwordEncoding(passwordOne, user);
                updateUser(user);
                log.info(USER_UPDATE_PASSWORD, user.getEmail());
                return REDIRECT + LOGIN;
            }
        }
        return null;
    }
}
package com.osypenko.services;

import com.osypenko.exception.UserException;
import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public void createAndUpdateUser(User user) {
        userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public User findByEmail(String email) {
        try {
            log.error("user " + email);
            return userRepo.findByEmail(email);
        } catch (UserException e) {
            log.error("user not found");
            throw new UserException();
        }
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = findById(id);
        return optionalUser.orElse(null);
    }
}

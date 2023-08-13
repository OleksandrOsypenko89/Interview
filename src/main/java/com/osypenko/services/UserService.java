package com.osypenko.services;

import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private HashMap<String, Long> hashMail;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public void createAndUpdateUser(User user) {
        userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public HashMap<String, Long> userHashMap() {
        if (hashMail == null) {
            hashMail = new HashMap<>();
            List<User> allUsers = getAll();
            for (User user : allUsers) {
                hashMail.put(user.getEmail(), user.getId());
            }
            return hashMail;
        }
        return hashMail;
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            log.error("user " + user);
            return user;
        }
        return null;
    }
}

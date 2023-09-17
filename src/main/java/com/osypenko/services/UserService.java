package com.osypenko.services;

import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        return userRepo.findByEmail(email);
    }

    public HashSet<String> allEmailUsers() {
        HashSet<String> allEmail = new HashSet<>();
        List<User> allUsers = getAll();
        for (User user : allUsers) {
            allEmail.add(user.getEmail());
        }
        return allEmail;
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = findById(id);
        return optionalUser.orElse(null);
    }
}

package com.osypenko.services;

import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

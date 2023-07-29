package com.osypenko.services;

import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;
    public List<User> getAll() {
        return userRepo.findAll();
    }

    public Optional<User> get(Integer id) {
        return userRepo.findById(id);
    }

    public void newUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userRepo.save(user);
    }
}

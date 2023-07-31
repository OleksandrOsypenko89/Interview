package com.osypenko.repository;

import com.osypenko.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}

package com.osypenko.services.user;

import com.osypenko.services.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static com.osypenko.TestConstants.*;

class UserDetailsServiceTest extends BaseTests {
    private final UserDetailsService userDetailsService;

    public UserDetailsServiceTest(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Test
    void loadUserByUsername() {
        UserDetails user = userDetailsService.loadUserByUsername(EXPECTED_USER_EMAIL);
        Assertions.assertEquals(EXPECTED_USER_EMAIL, user.getUsername());
        Assertions.assertEquals(EXPECTED_USER_PASSWORD, user.getPassword());
    }
}
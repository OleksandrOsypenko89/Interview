package com.osypenko.services.user;

import com.osypenko.services.BaseTests;
import com.osypenko.model.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.osypenko.constant.Endpoints.*;

class UserServiceTest extends BaseTests {
    private final UserService userService;

    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void getAll() {
        List<User> all = userService.getAll();
        Assertions.assertEquals(all.size(), ALL_USERS_SIZE);
    }

    @Test
    void findByEmail() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        Assertions.assertEquals(EXPECTED_USER_ID, user.getId());
        Assertions.assertEquals(EXPECTED_USER_EMAIL, user.getEmail());
        Assertions.assertEquals(EXPECTED_USER_PASSWORD, user.getPassword());
        Assertions.assertEquals(EXPECTED_USER_ROLE, user.getRole());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(expectedUser);
        Assertions.assertEquals(ALL_USERS_SIZE - 1, userService.getAll().size());
    }

    @Test
    void saveAndFlushUser() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        user.setLastName(TEST_LAST_NAME);
        userService.saveAndFlushUser(user);
        Assertions.assertEquals(user.getLastName(), TEST_LAST_NAME);
    }

    @Test
    void fromUserDetailsToUser() {
        User user = userService.fromUserDetailsToUser(userDetails);
        Assertions.assertEquals(EXPECTED_USER_EMAIL, user.getEmail());
        Assertions.assertEquals(EXPECTED_USER_PASSWORD, user.getPassword());
        Assertions.assertEquals(EXPECTED_USER_ROLE, user.getRole());
    }

    @Test
    void updateDate() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setEmail(TEST_EMAIL);
        userService.saveAndFlushUser(user);
        Assertions.assertEquals(TEST_FIRST_NAME, user.getFirstName());
        Assertions.assertEquals(TEST_LAST_NAME, user.getLastName());
        Assertions.assertEquals(TEST_EMAIL, user.getEmail());
    }

    @Test
    void createNewUser() {
        User user = userService.createNewUser(newUser, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        Assertions.assertEquals(TEST_FIRST_NAME, user.getFirstName());
        Assertions.assertEquals(TEST_LAST_NAME, user.getLastName());
        Assertions.assertEquals(TEST_EMAIL, user.getEmail());
        Assertions.assertNotEquals(TEST_PASSWORD, user.getPassword());
        Assertions.assertEquals(EXPECTED_USER_ROLE, user.getRole());
    }

    @Test
    void passwordEncoding() {
        userService.passwordEncoding(TEST_PASSWORD, expectedUser);
        Assertions.assertNotEquals(TEST_PASSWORD, expectedUser.getPassword());
    }

    @Test
    void userPasswordChange() {
        String str = userService.userPasswordChange(EXPECTED_USER_EMAIL, TEST_PASSWORD, TEST_PASSWORD);
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        Assertions.assertNotEquals(EXPECTED_USER_PASSWORD, user.getPassword());
        Assertions.assertEquals(REDIRECT + LOGIN, str);
        Assertions.assertNotEquals(REDIRECT + NEW_PASSWORD, str);
    }
}
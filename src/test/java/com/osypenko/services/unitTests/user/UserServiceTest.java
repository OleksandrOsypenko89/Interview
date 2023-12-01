package com.osypenko.services.unitTests.user;

import com.osypenko.model.users.Role;
import com.osypenko.model.users.User;
import com.osypenko.repository.UserRepository;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    private User userOne;
    private User userTwo;
    public static final long USER_ID_ONE = 1L;
    public static final long USER_ID_TWO = 2L;
    public static final String MOCK_MAIL_ONE = "mock_one_email@demo.com";
    public static final String MOCK_MAIL_TWO = "mock_two_email@demo.com";
    public static final String MOCK_PASSWORD_ONE = "mock_one_password";
    public static final String MOCK_PASSWORD_TWO = "mock_two_password";

    @BeforeEach
    void setup() {
        userTwo = User.builder()
                .id(USER_ID_ONE)
                .email(MOCK_MAIL_ONE)
                .password(MOCK_PASSWORD_ONE)
                .role(Role.USER)
                .build();
        userOne = User.builder()
                .id(USER_ID_TWO)
                .email(MOCK_MAIL_TWO)
                .password(MOCK_PASSWORD_TWO)
                .role(Role.USER)
                .build();
    }

    @Test
    void getAll() {
        Mockito.doAnswer(invocationOnMock -> List.of(userTwo, userOne))
                .when(userRepository)
                .findAll();
        List<User> users = userService.getAll();
        Assertions.assertEquals(2, users.size());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    void updateUser() {
        Mockito.doAnswer(invocationOnMock -> userTwo)
                .when(userRepository)
                .save(userTwo);
        userService.updateUser(userTwo);
        Mockito.verify(userRepository, Mockito.times(1)).save(userTwo);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findByEmail() {
        Mockito.doAnswer(invocationOnMock -> Optional.of(userTwo))
                .when(userRepository)
                .findByEmail(MOCK_MAIL_ONE);
        User user = userService.findByEmail(MOCK_MAIL_ONE).orElseThrow();
        Assertions.assertEquals(userTwo.getId(), user.getId());
        Assertions.assertEquals(userTwo.getEmail(), user.getEmail());
        Assertions.assertEquals(userTwo.getPassword(), user.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(MOCK_MAIL_ONE);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    void deleteUser() {
        Mockito.doNothing()
                .when(userRepository)
                .delete(userTwo);
        userService.deleteUser(userTwo);
        Mockito.verify(userRepository, Mockito.times(1)).delete(userTwo);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    void flushUser() {
        Mockito.doAnswer(invocationOnMock -> userTwo)
                .when(userRepository)
                .saveAndFlush(userTwo);
        userService.flushUser(userTwo);
        Mockito.verify(userRepository, Mockito.times(1)).saveAndFlush(userTwo);
        Mockito.verifyNoMoreInteractions(userRepository);
    }
}
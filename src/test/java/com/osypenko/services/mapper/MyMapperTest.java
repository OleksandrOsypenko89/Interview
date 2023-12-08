package com.osypenko.services.mapper;

import com.osypenko.BaseTests;
import com.osypenko.dto.UserDTO;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyMapperTest extends BaseTests {
    private final MyMapper myMapper;
    private final UserService userService;

    public MyMapperTest(MyMapper myMapper, UserService userService) {
        this.myMapper = myMapper;
        this.userService = userService;
    }

    @Test
    void createUserInUserDTO() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        UserDTO userInUserDTO = myMapper.createUserInUserDTO(user);
        Assertions.assertEquals(user.getId(), userInUserDTO.getId());
        Assertions.assertEquals(user.getFirstName(), userInUserDTO.getFirstName());
        Assertions.assertEquals(user.getLastName(), userInUserDTO.getLastName());
        Assertions.assertEquals(user.getEmail(), userInUserDTO.getEmail());
        Assertions.assertEquals(user.getPassword(), userInUserDTO.getPassword());
        Assertions.assertEquals(user.getRole(), userInUserDTO.getRole());
        Assertions.assertEquals(user.getRegistrationDate(), userInUserDTO.getRegistrationDate());
        Assertions.assertEquals(user.getListStudyQuestion().size(), userInUserDTO.getListStudyQuestion().size());
        Assertions.assertEquals(user.getStatistic().size(), userInUserDTO.getStatistic().size());
    }

    @Test
    void updateUser() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        UserDTO userInUserDTO = myMapper.updateUser(user, userDTO);
        Assertions.assertEquals(TEST_FIRST_NAME, userInUserDTO.getFirstName());
        Assertions.assertEquals(TEST_LAST_NAME, userInUserDTO.getLastName());
        Assertions.assertEquals(TEST_EMAIL, userInUserDTO.getEmail());
    }
}
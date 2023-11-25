package com.osypenko.controller.template.admin;

import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.USER_UPDATE;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UpdateOrDeleteUserController {

    private final UserService userService;

    @GetMapping(UPDATE_OR_DELETE_USER)
    public String feedback() {
        return DIRECTORY_ADMIN + UPDATE_OR_DELETE_USER;
    }

    @PostMapping(DELETE_USER)
    public String deleteUser(@SessionAttribute(USER_UPDATE) User user) {
        userService.deleteUser(user);
        return REDIRECT + ADMIN_PAGE;
    }
}

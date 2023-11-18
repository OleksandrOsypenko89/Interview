package com.osypenko.controller.rest.admin;

import com.osypenko.dto.InfoForAdmin;
import com.osypenko.model.users.User;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.interview.TestingService;
import com.osypenko.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.Endpoints.*;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
public class AdminRestController {
    private final UserService userService;
    private final QuestionService questionService;
    private final TestingService testingService;

    @GetMapping(INFO)
    public ResponseEntity<InfoForAdmin> getAllInfoUser(InfoForAdmin infoForAdmin) {
        infoForAdmin.setRegisteredUsers(userService.getAll().size());
        infoForAdmin.setQuestionSize(questionService.sizeAllQuestion());
        infoForAdmin.setTestingSize(testingService.sizeAllQuestion());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(infoForAdmin);
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<String> deleteStudyQuestion(@RequestBody String email) {
        String message;
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userService.deleteUser(user);
            message = USER_DELETED + email;
        } else {
            message = USER_EMAIL + email + NO_FIND;
        }

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(message);
    }
}

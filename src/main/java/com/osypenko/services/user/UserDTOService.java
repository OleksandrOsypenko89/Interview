package com.osypenko.services.user;

import com.osypenko.dto.UserDTO;
import com.osypenko.services.mapper.MyMapper;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.interview.TestingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDTOService {
    private final MyMapper myMapper;
    private final UserService userService;
    private final TestingService testingService;
    private final QuestionService questionService;

    public void saveRegistrationData(UserDTO registrationUserDTO, User newUser) {
        User user = userService.createNewUser(
                newUser
                , registrationUserDTO.getFirstName()
                , registrationUserDTO.getLastName()
                , registrationUserDTO.getEmail()
                , registrationUserDTO.getPassword()
        );
        userService.saveAndFlushUser(user);
    }

    public UserDTO updateDate(UserDetails userDetails, UserDTO updateUserDTO) {
        User user = userService.fromUserDetailsToUser(userDetails);
        userService.updateDate(
                user
                , updateUserDTO.getFirstName()
                , updateUserDTO.getLastName()
                , updateUserDTO.getEmail()
        );
        return myMapper.updateUser(user, updateUserDTO);
    }

    public UserDTO getUserDTO(UserDetails userDetails) {
        User user = userService.fromUserDetailsToUser(userDetails);
        questionService.listFilling(user);
        testingService.listFilling(user);
        return myMapper.createUserInUserDTO(user);
    }

    public UserDTO deleteStudyQuestionUser(UserDetails userDetails, QuestionInterview questionInterviewId) {
        User user = userService.fromUserDetailsToUser(userDetails);
        user.getListStudyQuestion().remove(questionInterviewId);
        userService.saveAndFlushUser(user);
        return myMapper.createUserInUserDTO(user);
    }
}

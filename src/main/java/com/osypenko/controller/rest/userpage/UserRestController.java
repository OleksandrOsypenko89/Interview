package com.osypenko.controller.rest.userpage;

import com.osypenko.dto.UserDTO;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.services.user.UserDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.osypenko.constant.Endpoints.*;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
public class UserRestController {
    private final UserDTOService userDTOService;

    @GetMapping(USER_API)
    public ResponseEntity<UserDTO> getAllInfoUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserDTO userDTO = userDTOService.getUserDTO(userDetails);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTO);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<UserDTO> updateUser(
            @AuthenticationPrincipal UserDetails userDetails
            , @RequestBody UserDTO updateUserDTO
    ) {
        UserDTO userDTO = userDTOService.updateDate(userDetails, updateUserDTO);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userDTO);
    }

    @DeleteMapping(DELETE_STUDY_QUESTION)
    public ResponseEntity<UserDTO> deleteStudyQuestion(
            @AuthenticationPrincipal UserDetails userDetails
            , @RequestBody QuestionInterview questionInterviewId
    ) {
        UserDTO userDTO = userDTOService.deleteStudyQuestionUser(userDetails, questionInterviewId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userDTO);
    }
}
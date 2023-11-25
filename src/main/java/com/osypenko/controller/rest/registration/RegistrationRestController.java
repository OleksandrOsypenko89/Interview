package com.osypenko.controller.rest.registration;

import com.osypenko.dto.UserDTO;
import com.osypenko.services.mapper.MyMapper;
import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import com.osypenko.services.user.UserDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.osypenko.constant.Constant.NEW_USER_FROM_API;
import static com.osypenko.constant.Constant.OLEKSANDR_GMAIL_COM;
import static com.osypenko.constant.Endpoints.*;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
public class RegistrationRestController {
    private final UserDTOService userDTOService;
    private final MailService mailService;
    private final MyMapper myMapper;

    @PostMapping(REGISTRATION)
    public ResponseEntity<UserDTO> getUserRegistration(
            @RequestBody UserDTO registrationUserDTO
            , User user
    ) {
        userDTOService.saveRegistrationData(registrationUserDTO, user);
        mailService.sendSimpleMessage(OLEKSANDR_GMAIL_COM, NEW_USER_FROM_API);
        UserDTO userDTO = myMapper.updateUserInUserDTO(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userDTO);
    }
}

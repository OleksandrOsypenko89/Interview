package com.osypenko.controller.rest.registration;

import com.osypenko.controller.rest.exeption.ErrorApiMessage;
import com.osypenko.dto.RegistrationUserDTO;
import com.osypenko.dto.UserDTO;
import com.osypenko.mapper.MyMapper;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.osypenko.constant.Endpoints.*;

@RestController
@RequiredArgsConstructor
public class RegistrationRestController {
    private final UserService userService;
    private final MyMapper myMapper;

    @PostMapping(API_REGISTRATIONS)
    public ResponseEntity<?> getUserRegistration(@RequestBody RegistrationUserDTO registrationUserDTO, User user) {
        try {
            userService.createNewUser(
                    user
                    , registrationUserDTO.getFirstName()
                    , registrationUserDTO.getLastName()
                    , registrationUserDTO.getEmail()
                    , registrationUserDTO.getPassword()
            );
            userService.flushUser(user);

            UserDTO userDTO = myMapper.getUserDTO(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorApiMessage().errorMessage(registrationUserDTO.getEmail()));
        }
    }
}

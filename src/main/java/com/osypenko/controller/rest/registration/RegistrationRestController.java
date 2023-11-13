package com.osypenko.controller.rest.registration;

import com.osypenko.dto.RegistrationUserDTO;
import com.osypenko.dto.UserDTO;
import com.osypenko.mapper.MyMapper;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.osypenko.constant.Endpoints.*;

@RestController
@RequiredArgsConstructor
public class RegistrationRestController {
    private final UserDTOService userDTOService;
    private final MyMapper myMapper;

    @PostMapping(API_REGISTRATIONS)
    public ResponseEntity<UserDTO> getUserRegistration(
            @RequestBody RegistrationUserDTO registrationUserDTO
            , User user
    ) {
        userDTOService.saveRegistrationData(registrationUserDTO, user);

        UserDTO userDTO = myMapper.getUserDTO(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userDTO);
    }
}

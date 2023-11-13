package com.osypenko.services.user;

import com.osypenko.dto.RegistrationUserDTO;
import com.osypenko.dto.UpdateUserDTO;
import com.osypenko.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDTOService {
    private final UserService userService;

    public void saveRegistrationData(RegistrationUserDTO registrationUserDTO, User user) {
        userService.createNewUser(
                user
                , registrationUserDTO.getFirstName()
                , registrationUserDTO.getLastName()
                , registrationUserDTO.getEmail()
                , registrationUserDTO.getPassword()
        );
        userService.flushUser(user);
    }

    public void updateDate(User user, UpdateUserDTO updateDateUserDTO) {
        userService.updateDate(
                user
                , updateDateUserDTO.getFirstName()
                , updateDateUserDTO.getLastName()
                , updateDateUserDTO.getEmail()
        );
        userService.flushUser(user);
    }
}

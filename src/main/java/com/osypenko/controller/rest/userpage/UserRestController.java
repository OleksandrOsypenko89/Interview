package com.osypenko.controller.rest.userpage;

import com.osypenko.dto.UpdateUserDTO;
import com.osypenko.mapper.MyMapper;
import com.osypenko.dto.UserDTO;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserDTOService;
import com.osypenko.services.user.UserDetailsService;
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
    private final UserDetailsService userDetailsService;
    private final UserDTOService userDTOService;
    private final MyMapper myMapper;

    @GetMapping(USER_API)
    public ResponseEntity<UserDTO> getAllInfoUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDetailsService.getUser(userDetails);
        UserDTO userDTO = myMapper.getUserDTO(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTO);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<UserDTO> updateUser(
            @AuthenticationPrincipal UserDetails userDetails
            , @RequestBody UpdateUserDTO updateUserDTO
    ) {
        User user = userDetailsService.getUser(userDetails);
        UserDTO updateUser = myMapper.updateUser(user, updateUserDTO);
        userDTOService.updateDate(user, updateUserDTO);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(updateUser);
    }
}

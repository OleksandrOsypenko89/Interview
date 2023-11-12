package com.osypenko.controller.rest.userpage;

import com.osypenko.mapper.MyMapper;
import com.osypenko.dto.UserDTO;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.osypenko.constant.Endpoints.*;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
public class UserPageRestController {
    private final UserDetailsService userDetailsService;
    private final MyMapper myMapper;

    @GetMapping(USER_API)
    public ResponseEntity<UserDTO> getUserInApi(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDetailsService.getUser(userDetails);
        UserDTO userDTO = myMapper.getUserDTO(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTO);
    }
}

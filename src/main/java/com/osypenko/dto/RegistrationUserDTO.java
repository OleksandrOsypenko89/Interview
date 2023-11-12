package com.osypenko.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

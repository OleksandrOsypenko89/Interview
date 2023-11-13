package com.osypenko.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String email;
}

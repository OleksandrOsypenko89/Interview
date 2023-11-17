package com.osypenko.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InfoForAdmin {
    private int registeredUsers;
    private int questionSize;
    private int testingSize;
}

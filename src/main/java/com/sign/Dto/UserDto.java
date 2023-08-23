package com.sign.Dto;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private boolean status;
    private String salt;
    private LocalDateTime registrationDatetime;
    private LoginInfo loginInfo;
}


package com.sign.Dto;


import lombok.*;

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

}


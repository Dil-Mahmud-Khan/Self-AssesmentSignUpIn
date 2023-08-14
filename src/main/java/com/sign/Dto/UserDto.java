package com.sign.Dto;


import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userId;
    private String userName;
    private String email;
    private String password;

}


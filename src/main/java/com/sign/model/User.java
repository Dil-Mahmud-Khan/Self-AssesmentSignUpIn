package com.sign.model;


import com.sign.Dto.LoginInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean status;
    private String salt;
    @Column(name="registration_datetime")
    private LocalDateTime registrationDatetime;
}

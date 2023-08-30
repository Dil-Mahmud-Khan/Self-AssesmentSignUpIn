package com.project2morrow.lapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private  String email;
    private String password;
    private String salt;
    private boolean status;
    private boolean canUploadVideo;
    private boolean canUploadDoc;
    private boolean canUploadQues;
    private boolean canCreateUser;
    private boolean canHoldUser;
    private boolean canModifyUser;
    private boolean canDeleteUser;
}

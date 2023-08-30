package com.project2morrow.lapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
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

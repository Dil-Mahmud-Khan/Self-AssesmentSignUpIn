package com.project2morrow.lapp.Service;

import com.project2morrow.lapp.Response.LoginResponse;
import com.project2morrow.lapp.Dto.LoginDto;
import com.project2morrow.lapp.Dto.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface UserService {
      String addUser(UserDto userDto) ;

      LoginResponse loginUser(LoginDto loginDto);

      long countRegisteredUsers();
      LocalDateTime getRegistrationDatetime(String email);
      void updateLastLoginTime(String email);

    long countLoginsInDay(LocalDate date);
}

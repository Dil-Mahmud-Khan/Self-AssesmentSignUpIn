package com.sign.Service;

import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Response.LoginResponse;
import org.springframework.stereotype.Service;

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

package com.sign.Service;

import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Response.LoginResponse;
import org.springframework.stereotype.Service;


public interface UserService {
      String addUser(UserDto userDto) ;

      LoginResponse loginUser(LoginDto loginDto);
}

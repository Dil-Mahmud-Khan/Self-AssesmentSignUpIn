package com.sign.Service;

import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Repository.UserRepository;
import com.sign.Response.LoginResponse;
import com.sign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;


    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 //   private  PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDto userDto) {
       User user= new User(
               userDto.getUserId(),
               userDto.getUserName(),
               userDto.getEmail(),
              passwordEncoder().encode(userDto.getPassword()
               ), userDto.isStatus()
       );
       userRepository.save(user);
       return "inserted";
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDto.getEmail());
        if (user1 != null) {
            String password = loginDto.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder().matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDto.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {

                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits!", false);
        }

    }
}

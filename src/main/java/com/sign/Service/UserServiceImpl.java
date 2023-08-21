package com.sign.Service;

import com.sign.Config.CustomerUserDetails;
import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Repository.UserRepository;
import com.sign.Response.LoginResponse;
import com.sign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;
    private String username;


    @Override
    public UserDto addUser(UserDto userDto) {
       User user=new User(userDto.getUserId(),
               userDto.getUserName(),
               userDto.getEmail(),
               userDto.getPassword(),
               userDto.getRole(),
               userDto.isEnabled());
        User save = userRepository.save(user);
        return userDto;

    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user1= userRepository.getUsersByUserName(username);

        if(user1==null){
            throw  new UsernameNotFoundException("Could not found user!!");
        }
        CustomerUserDetails customerUserDetails=new CustomerUserDetails(user1);

        return customerUserDetails;
    }

    //   @Override
//    public LoginResponse loginUser(LoginDto loginDto) {
//        String msg = "";
//        User user1 = userRepository.findByEmail(loginDto.getEmail());
//        if (user1 != null) {
//            String password = loginDto.getPassword();
//            String encodedPassword = user1.getPassword();
//            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
//            if (isPwdRight) {
//                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDto.getEmail(), encodedPassword);
//                if (user.isPresent()) {
//                    return new LoginResponse("Login Success", true);
//                } else {
//                    return new LoginResponse("Login Failed", false);
//                }
//            } else {
//
//                return new LoginResponse("password Not Match", false);
//            }
//        }else {
//            return new LoginResponse("Email not exits!!", false);
//        }
//
//    }



}

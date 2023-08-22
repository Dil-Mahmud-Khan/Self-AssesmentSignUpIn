package com.sign.Service;

import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Repository.UserRepository;
import com.sign.Response.LoginResponse;
import com.sign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements  UserService {

//    @Autowired
//    private UserRepository userRepository;
//
//
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
// //   private  PasswordEncoder passwordEncoder;
//
//    @Override
//    public String addUser(UserDto userDto) {
//       User user= new User(
//               userDto.getUserId(),
//               userDto.getUserName(),
//               userDto.getEmail(),
//              passwordEncoder().encode(userDto.getPassword()
//               ), userDto.isStatus()
//       );
//       userRepository.save(user);
//       return "inserted";
//    }
//
//    @Override
//    public LoginResponse loginUser(LoginDto loginDto) {
//        String msg = "";
//        User user1 = userRepository.findByEmail(loginDto.getEmail());
//        if (user1 != null) {
//            String password = loginDto.getPassword();
//            String encodedPassword = user1.getPassword();
//            Boolean isPwdRight = passwordEncoder().matches(password, encodedPassword);
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
//            return new LoginResponse("Email not exits!", false);
//        }
//
//    }

        @Autowired
        private UserRepository userRepository;
         private static final String SECRET_PEPPER = "dil";

        @Override
        public String addUser(UserDto userDto) {
            String salt = generateSalt();
            String hashedPassword = hashPassword(userDto.getPassword(), salt);

            User user = new User(
                    userDto.getUserId(),
                    userDto.getUserName(),
                    userDto.getEmail(),
                    hashedPassword,
                    userDto.isStatus(),
                    salt);

            userRepository.save(user);
            return "Registration successful!";
        }


        @Override
        public LoginResponse loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user != null) {
            String hashedPasswordInput = hashPassword(loginDto.getPassword(),user.getSalt());

            if (BCrypt.checkpw(hashedPasswordInput, loginDto.getPassword())) {
                return new LoginResponse("Login successful!", true);
            } else {
                return new LoginResponse("Invalid credentials.", false);
            }
        } else {
            return new LoginResponse("User not found.", false);
        }
    }


    private String generateSalt() {
            SecureRandom random = new SecureRandom();
            byte[] saltBytes = new byte[16]; // 16 bytes for salt
            random.nextBytes(saltBytes);
            return Base64.getEncoder().encodeToString(saltBytes);
        }

        private String hashPassword(String password, String salt) {
            String combinedValue = SECRET_PEPPER + password + salt;
            return BCrypt.hashpw(combinedValue, BCrypt.gensalt());
        }
    }

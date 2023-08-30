package com.project2morrow.lapp.Service;

import com.project2morrow.lapp.Dto.LoginDto;
import com.project2morrow.lapp.Dto.LoginInfo;
import com.project2morrow.lapp.Dto.UserDto;
import com.project2morrow.lapp.Repository.LoginRepository;
import com.project2morrow.lapp.Repository.UserRepository;
import com.project2morrow.lapp.Response.LoginResponse;
import com.project2morrow.lapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
        String randomString = generateRandomString(16);
    private static final String PEPPER = generateRandomString(16);



    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16]; // 16 bytes for salt
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String hashPassword(String password, String salt, String pepper) {
        String combinedValue = pepper + password + salt;
        // Implement hashing logic (e.g., using BCrypt)
        return combinedValue;
    }

    public long countRegisteredUsers() {
        return userRepository.count();
    }

    @Override
    public LocalDateTime getRegistrationDatetime(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getRegistrationDatetime();
        }
        return null; // User not found
    }

    @Override
    public void updateLastLoginTime(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            LoginInfo loginInfo = loginRepository.findByEmail(email);
            if (loginInfo == null) {
                loginInfo = new LoginInfo();
                loginInfo.setEmail(email);
            }
            loginInfo.setLastLoginTime(LocalDateTime.now());
            loginRepository.save(loginInfo);
        }
    }


    @Override
    public String addUser(UserDto userDto) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(userDto.getPassword(), salt, PEPPER);

        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                hashedPassword,
                userDto.isStatus(),
                salt,
                LocalDateTime.now()
        );

        userRepository.save(user);
     //   String otp= MyOTPgen.generateOTP();
        return "Registration successful!";
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user != null) {
            String hashedPassword = hashPassword(loginDto.getPassword(), user.getSalt(), PEPPER);
            if (hashedPassword.equals(user.getPassword())) {
                updateLastLoginTime(loginDto.getEmail());
                return new LoginResponse("Login Success", true);
            } else {
                return new LoginResponse("Login Failed", false);
            }
        } else {
            return new LoginResponse("User not found.", false);
        }
    }


    public long countLoginsInDay(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return loginRepository.countByLastLoginTimeBetween(startOfDay, endOfDay);
    }

    public LoginInfo findLoginByEmail(String email) {
        return loginRepository.findByEmail(email);
    }
}

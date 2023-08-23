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

    @Autowired
    private UserRepository userRepository;


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

    @Override
    public String addUser(UserDto userDto) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(userDto.getPassword(), salt, PEPPER);

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
            String hashedPassword = hashPassword(loginDto.getPassword(), user.getSalt(), PEPPER);

            if (hashedPassword.equals(user.getPassword())) {
                return new LoginResponse("Login Success", true);
            } else {
                return new LoginResponse("Login Failed", false);
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

    private String hashPassword(String password, String salt, String pepper) {
        String combinedValue = pepper + password + salt;
        // Implement hashing logic (e.g., using BCrypt)

        return combinedValue;
    }
}

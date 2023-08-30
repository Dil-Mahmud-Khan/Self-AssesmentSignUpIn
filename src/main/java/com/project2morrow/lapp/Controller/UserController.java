package com.project2morrow.lapp.Controller;


import com.project2morrow.lapp.Dto.LoginDto;
import com.project2morrow.lapp.Dto.UserDto;
import com.project2morrow.lapp.Response.LoginResponse;
import com.project2morrow.lapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@CrossOrigin()
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public String saveEmployee(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {

        LoginResponse loginResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/id/registration-datetime")
    public ResponseEntity<String> getUserRegistrationDatetime(@RequestParam String email) {
        LocalDateTime registrationDatetime = userService.getRegistrationDatetime(email);
        if (registrationDatetime != null) {
            return ResponseEntity.ok(registrationDatetime.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        long userCount = userService.countRegisteredUsers();
        return ResponseEntity.ok(userCount);
    }
    @GetMapping("/total-login")
    public ResponseEntity<Long> countLoginsInDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        long loginCount = userService.countLoginsInDay(date);
        return ResponseEntity.ok(loginCount);
    }
}

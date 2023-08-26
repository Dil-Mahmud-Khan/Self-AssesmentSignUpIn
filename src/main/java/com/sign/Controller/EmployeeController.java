package com.sign.Controller;


import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Response.LoginResponse;
import com.sign.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("api/user")
public class EmployeeController {

    @Autowired
    private UserService userService;

    @Autowired
    EmployeeController(UserService userService) {
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

//    @GetMapping("/id/registration-datetime")
//    public ResponseEntity<String> getUserRegistrationDatetime(@RequestParam String email) {
//        LocalDateTime registrationDatetime = userService.getRegistrationDatetime(email);
//        if (registrationDatetime != null) {
//            return ResponseEntity.ok(registrationDatetime.toString());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @GetMapping("/count")
//    public ResponseEntity<Long> getUserCount() {
//        long userCount = userService.countRegisteredUsers();
//        return ResponseEntity.ok(userCount);
//    }



}

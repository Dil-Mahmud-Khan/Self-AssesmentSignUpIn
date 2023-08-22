package com.sign.Controller;


import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Response.LoginResponse;
import com.sign.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("api/user")
public class EmployeeController {

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

}

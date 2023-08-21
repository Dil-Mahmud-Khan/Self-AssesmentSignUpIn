package com.sign.Controller;


import com.sign.Dto.LoginDto;
import com.sign.Dto.UserDto;
import com.sign.Repository.UserRepository;
import com.sign.Response.LoginResponse;
import com.sign.Service.UserService;
import com.sign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("api/user")
public class EmployeeController {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @Autowired
    EmployeeController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/save")
    public UserDto saveEmployee(@RequestBody UserDto userDto){
        UserDto save1 = this.userService.addUser(userDto);

        return save1;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        ///    System.out.println(loginDto.g);
        LoginResponse loginResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }

}

package com.project2morrow.lapp.Controller;

import com.project2morrow.lapp.Dto.AdminDto;
import com.project2morrow.lapp.Service.AdminService;
import com.project2morrow.lapp.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminService adminService;


    @PostMapping("/add")
    public String AddAdmin(@RequestBody AdminDto adminDto){
       return adminService.addAdmin(adminDto);
    }

    @PutMapping("/update/{id}")
    public Admin UpdateAdmin(@RequestBody Admin admin, @PathVariable Integer id){
        return adminService.updateAdmin(admin,id);
    }

    @GetMapping("/getadmins")
    public ResponseEntity<List<Admin>> getAdmins(){
        return new ResponseEntity<>(
            adminService.getAdmins(), HttpStatus.FOUND
        );
    }

    @GetMapping("/getadmin/{id}")
    public Optional<Admin> getAdminById(@PathVariable  Integer id) {
        return adminService.getAdminById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void DeleteAdmin(@PathVariable Integer id){
        adminService.deleteAdmin(id);
    }


}

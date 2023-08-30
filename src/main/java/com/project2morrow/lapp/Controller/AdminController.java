package com.project2morrow.lapp.Controller;

import com.project2morrow.lapp.Dto.AdminDto;
import com.project2morrow.lapp.Service.AdminService;
import com.project2morrow.lapp.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminService adminService;


    @PostMapping("/add")
    public ResponseEntity<String> AddAdmin(@RequestBody AdminDto adminDto){
        String b=null;
        try {
            b=this.adminService.addAdmin(adminDto);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin,@PathVariable("id") Integer id) {
        try {
          //  Admin adminRes=
            adminService.updateAdmin(admin,id);
            return ResponseEntity.ok().body(admin);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getadmins")
    public ResponseEntity<List<Admin>> getAdmins(){
        List<Admin> list=adminService.getAdmins();
        if(list.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/getadmin/{id}")
    public ResponseEntity<Optional<Admin>> getAdmin(@PathVariable("id") int id) {
        Optional<Admin> admin=adminService.getAdminById(id);
        if(admin==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(admin));
    }

   @DeleteMapping("delete/{id}")
    public ResponseEntity<Void>DeleteAdmin(@PathVariable Integer id){
        try
        {
            this.adminService.deleteAdmin(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
   }
}

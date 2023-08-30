package com.project2morrow.lapp.Service;

import com.project2morrow.lapp.Dto.AdminDto;
import com.project2morrow.lapp.model.Admin;

import java.util.List;
import java.util.Optional;


public interface AdminService {
    String addAdmin(AdminDto adminDto);

    List<Admin> getAdmins();

    Optional<Admin> getAdminById(Integer id);

    Admin updateAdmin(Admin admin,Integer id);

    void deleteAdmin(Integer id);

}

package com.project2morrow.lapp.Service;

import com.project2morrow.lapp.Dto.AdminDto;
import com.project2morrow.lapp.Repository.AdminRepository;
import com.project2morrow.lapp.exception.AdminNotFoundException;
import com.project2morrow.lapp.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    
    
    private AdminDto adminDto;

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


    @Override
    public String addAdmin(AdminDto adminDto) {

        String salt=generateSalt();
        String hashedPassowrd=hashPassword(adminDto.getPassword(), salt, PEPPER);

        Admin admin1=new Admin(
                adminDto.getId(),
                adminDto.getName(),
                adminDto.getEmail(),
                hashedPassowrd,
                salt,
                adminDto.isStatus(),
                adminDto.isCanUploadVideo(),
                adminDto.isCanUploadDoc(),
                adminDto.isCanUploadQues(),
                adminDto.isCanCreateUser(),
                adminDto.isCanHoldUser(),
                adminDto.isCanModifyUser(),
                adminDto.isCanDeleteUser()
        );
         adminRepository.save(admin1);
         return "saved";
    }

    @Override
    public List<Admin> getAdmins() {
        return (List<Admin>)adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Integer id) {
        return Optional.ofNullable(adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("Admin Not Found")));
    }

    @Override
    public Admin updateAdmin(Admin admin,Integer id) {
        admin.setId(id);
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Integer id) {
        if(!adminRepository.existsById(id)){
            throw  new AdminNotFoundException("Admin not found");
        }
    adminRepository.deleteById(id);
    }
}

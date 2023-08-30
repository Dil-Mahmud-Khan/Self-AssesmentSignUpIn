package com.project2morrow.lapp.Repository;

import com.project2morrow.lapp.model.Admin;
import com.project2morrow.lapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    //Optional<Admin> findOneByEmailAndPassword(String email, String password);
    Admin findByEmail(String email);
}

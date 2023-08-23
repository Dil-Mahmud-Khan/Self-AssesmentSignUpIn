package com.sign.Repository;

import com.sign.Dto.LoginInfo;
import com.sign.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface LoginRepository extends JpaRepository<LoginInfo,Integer> {
  //  Optional<User> findOneByEmailAndPassword(String email, String password);

    LoginInfo findByEmail(String email);
}
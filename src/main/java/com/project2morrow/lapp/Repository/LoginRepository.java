package com.project2morrow.lapp.Repository;

import com.project2morrow.lapp.Dto.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@EnableJpaRepositories
@Repository
public interface LoginRepository extends JpaRepository<LoginInfo,Integer> {
  long countByLastLoginTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    LoginInfo findByEmail(String email);
}

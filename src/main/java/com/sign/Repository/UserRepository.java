package com.sign.Repository;

import com.sign.Dto.UserDto;
import com.sign.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

     Optional<User> findOneByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    @Query("select u from User  u where u.email=:email")
    public UserDto getUsersByUserName(@Param("email") String email);
}

package com.example.recofilm.repository;

import com.example.recofilm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // userService용
    UserDetails readByUsername(String username);

    //username 중복확인을 위한 쿼리
    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);

}

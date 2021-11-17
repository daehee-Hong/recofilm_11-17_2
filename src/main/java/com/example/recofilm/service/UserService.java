package com.example.recofilm.service;

import com.example.recofilm.dto.UserDto;
import com.example.recofilm.entity.User;
import com.example.recofilm.repository.UserRepository;
import com.example.recofilm.userdetails.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // 회원가입
    @Transactional
    public Long join(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // 변환
        User user = userDto.toEntity();
        // db저장
        return userRepository.save(user).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // user정보를 조회
        Optional<User> userWrapper = userRepository.findByUsername(username);
        User user = userWrapper.get();
        //userdetails 생성
        UserCustom userCustom = new UserCustom();

        // 해당하는 유저가 있는지 확인
        if(user==null) {
            throw new UsernameNotFoundException("아이디가 없습니다" + username);
        }
        // admin이면 admin권한
        if(username.equals("admin@admin")) {
            userCustom.setId(user.getId());
            userCustom.setUsername(user.getUsername());
            userCustom.setPassword(user.getPassword());
            userCustom.setNickname(user.getNickname());
            userCustom.setTelnumber(user.getTelnumber());
            userCustom.setRoles("ROLE_ADMIN");

        }else {
            userCustom.setId(user.getId());
            userCustom.setUsername(user.getUsername());
            userCustom.setPassword(user.getPassword());
            userCustom.setNickname(user.getNickname());
            userCustom.setTelnumber(user.getTelnumber());
            userCustom.setRoles("ROLE_USER");
        }

        // 반환
        return userCustom;

    }


}

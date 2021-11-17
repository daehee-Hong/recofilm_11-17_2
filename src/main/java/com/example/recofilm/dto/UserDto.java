package com.example.recofilm.dto;

import com.example.recofilm.entity.User;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;
    private String telnumber;

    @Builder
    public UserDto(Long id, String username, String password, String nickname, String telnumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.telnumber = telnumber;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickname(nickname)
                .telnumber(telnumber)
                .build();
    }
}

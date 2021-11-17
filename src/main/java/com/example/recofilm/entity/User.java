package com.example.recofilm.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String telnumber;

    @Builder
    public User(Long id, String username, String password, String nickname, String telnumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.telnumber = telnumber;
    }

}

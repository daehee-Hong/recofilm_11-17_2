package com.example.recofilm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    @Column(nullable = false)
    private String write_type;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false)
    private String nickname;
    @Column(updatable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private int view_count;

    @Builder
    public Board(Long num, String write_type, String title, String content, String nickname, int view_count, LocalDateTime date) {
        this.num = num;
        this.write_type = write_type;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.view_count = view_count;
        this.date = date;
    }
}

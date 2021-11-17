package com.example.recofilm.dto;

import com.example.recofilm.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Long num;
    private String write_type;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime date;
    private int view_count;

    public Board toEntity() {
        return Board.builder()
                .num(num)
                .write_type(write_type)
                .title(title)
                .content(content)
                .nickname(nickname)
                .view_count(view_count)
                .date(date)
                .build();
    }
    @Builder
    public BoardDto(Long num, String write_type, String title, String content, String nickname, LocalDateTime date, int view_count) {
        this.num = num;
        this.write_type = write_type;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
        this.view_count = view_count;
    }
}

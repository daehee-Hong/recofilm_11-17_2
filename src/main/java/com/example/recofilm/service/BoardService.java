package com.example.recofilm.service;

import com.example.recofilm.dto.BoardDto;
import com.example.recofilm.entity.Board;
import com.example.recofilm.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    // 글저장
    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getNum();
    }
    // 글 불러오기
    @Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "num"));
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .num(board.getNum())
                    .write_type(board.getWrite_type())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .nickname(board.getNickname())
                    .date(board.getDate())
                    .view_count(board.getView_count())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    // 글 보여주기
    @Transactional
    public BoardDto getPost(Long num) {
        //조회수 1증가
        //if(view_count == true){
        boardRepository.findbyIdView_count(num);

        Board board = boardRepository.findById(num).get();

        BoardDto boardDto = BoardDto.builder()
                .num(board.getNum())
                .write_type(board.getWrite_type())
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(board.getNickname())
                .date(board.getDate())
                .view_count(board.getView_count())
                .build();
        return boardDto;
    }
    // 글 삭제하기
    @Transactional
    public void deletePost(Long num) {
        boardRepository.deleteById(num);
    }

    @Transactional
    public BoardDto deleteCheck(Long num){
        Board board = boardRepository.findById(num).get();

        BoardDto boardDto = BoardDto.builder()
                .num(board.getNum())
                .write_type(board.getWrite_type())
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(board.getNickname())
                .date(board.getDate())
                .view_count(board.getView_count())
                .build();

        return boardDto;
    }
}

package com.example.recofilm.controller;

import com.example.recofilm.dto.BoardDto;
import com.example.recofilm.entity.Board;
import com.example.recofilm.service.BoardService;
import com.example.recofilm.userdetails.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    private boolean view_count;

    @GetMapping("/board")
    public String board(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("list", boardDtoList);
        return "board/board";
    }

    @GetMapping("/write")
    public String write(){ return "board/write"; }

    // 수정페이지
    @GetMapping("/edit")
    public String edit(@RequestParam Long num, Model model) {
        view_count = false;
        BoardDto boardDto = boardService.getPost(num);
        model.addAttribute("post", boardDto);
        return "board/edit";
    }
    // 수정
    @PostMapping("/edit")
    public String editPost(@AuthenticationPrincipal UserCustom user,
                           BoardDto boardDto, Model model) {
        if(user == null){
            model.addAttribute("msg", "먼저 로그인 해주세요.");
            return "user/msg";
        }
        // 로그인된 회원의 닉네임과 글쓴사람의 닉네임이 같지않으면
        if(!user.getNickname().equals(boardDto.getNickname())) {
            model.addAttribute("msg", "글쓴이만 수정가능합니다.");
            return "user/msg";
        }
        // 공백방지
        if(boardDto.getTitle() == null || boardDto.getTitle().length() <= 0 || boardDto.getTitle().equals("") ||
                boardDto.getContent() == null || boardDto.getContent().length() <= 0 || boardDto.getContent().equals(""))
        {
            model.addAttribute("msg", "공백이 있으면 안됍니다.");
            return "user/msg";
        }
        // date저장
        LocalDateTime currentDate = LocalDateTime.now();
        boardDto.setDate(currentDate);

        boardService.savePost(boardDto);
        return "redirect:/board";
    }
    // 글작성
    @PostMapping("/write")
    public String writePost(@AuthenticationPrincipal UserCustom user , BoardDto boardDto, Model model){
        // 회원이 아니면 글쓰기 방지
        if(user != null){ // 회원이 로그인 중일 때
            boardDto.setNickname(user.getNickname());
        }else {
            model.addAttribute("msg", "회원만 글쓰기가 가능합니다.");
            return "user/msg";
        }
        // 공백방지
        if(boardDto.getTitle() == null || boardDto.getTitle().length() <= 0 || boardDto.getTitle().equals("") ||
            boardDto.getContent() == null || boardDto.getContent().length() <= 0 || boardDto.getContent().equals(""))
        {
            model.addAttribute("msg", "공백이 있으면 안됍니다.");
            return "user/msg";
        }
        // date저장
        LocalDateTime currentDate = LocalDateTime.now();
        boardDto.setDate(currentDate);
        // 저장
        boardService.savePost(boardDto);
        return "redirect:/board"; }
    // 글보기
    @GetMapping("/view")
    public String view(@RequestParam Long num, Model model) {
        view_count = true;
        BoardDto boardDto = boardService.getPost(num);
        model.addAttribute("post", boardDto);
        return "board/view";
    }
    @GetMapping("/delete")
    public String delete(@AuthenticationPrincipal UserCustom user,
                         @RequestParam Long num, Model model) {
        // 글번호 값에 해당하는 dto가져오기
        BoardDto boardDto = boardService.deleteCheck(num);

        if(user == null){
            model.addAttribute("msg", "먼저 로그인 해주세요.");
            return "user/msg";
        }
        // 로그인된 회원의 닉네임과 글쓴사람의 닉네임이 같지않으면
        if(!user.getNickname().equals(boardDto.getNickname())) {
            model.addAttribute("msg", "글쓴이만 삭제가능합니다.");
            return "user/msg";
        }
        boardService.deletePost(num);
        return "redirect:/board";
    }

}

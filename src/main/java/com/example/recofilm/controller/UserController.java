package com.example.recofilm.controller;

import com.example.recofilm.dto.UserDto;
import com.example.recofilm.entity.User;
import com.example.recofilm.repository.UserRepository;
import com.example.recofilm.service.UserService;

import com.example.recofilm.userdetails.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/login")
    public String login(){ return "user/login"; }

    @GetMapping("/join")
    public String join(){ return "user/join"; }

    //회원가입
    @PostMapping("/join")
    public String register(UserDto userDto, Model model) {
        // 이메일 닉네임 중복확인
        String username = userDto.getUsername();
        String nickname = userDto.getNickname();
        String telnumber = userDto.getTelnumber();
        if(username == null || "".equals(username)|| nickname == null || "".equals(nickname) ||
                "".equals(telnumber) || telnumber == null){
            System.out.println("아이디, 닉네임, 전화번호가 공백이 있으면 안됍니다.");
            model.addAttribute("msg", "아이디, 닉네임, 전화번호가 공백이 있으면 안됍니다.");
            return "user/msg";
        }
        if(userRepository.existsByUsername(username) || userRepository.existsByNickname(nickname)){
            System.out.println("이메일 혹은 닉네임이 중복입니다");
            model.addAttribute("msg", "이메일 혹은 닉네임이 중복입니다");
            return "user/msg";
        }
        // 중복이 아닐경우 db에 저장
        userService.join(userDto);
        return "user/join_success";

    }
    //로그인
    @PostMapping("/login")
    public String loginForm() {
        return "redirect:/";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 인증된사용자면 로그아웃(세션 없애주고 권한 없애줌)
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/";
    }

    @GetMapping("id_find")
    public String id_find(){ return "user/id_find"; }

    @GetMapping("id_find_success")
    public String id_find_success(){ return "user/id_find_success"; }

    @GetMapping("pw_find")
    public String pw_find(){ return "user/pw_find"; }

    @GetMapping("pw_find_success")
    public String pw_find_success(){ return "user/pw_find_success"; }
}

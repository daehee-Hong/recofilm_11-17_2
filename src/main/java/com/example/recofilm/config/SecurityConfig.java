package com.example.recofilm.config;

import com.example.recofilm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) { // 4
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/font/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 5
        http.authorizeRequests() // 6
                .antMatchers("/**").permitAll() // 누구나 접근 허용
//                .antMatchers("/board").hasRole("USER") // USER, ADMIN만 접근 가능
//                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
//                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
             .formLogin() //로그인설정
                .loginPage("/login") // 로그인 페이지 링크
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
             .logout() // 8
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
//                .invalidateHttpSession(true) // 세션 날리기
                .deleteCookies("JSESSIONID,SPRING_SECURITY_REMEMBER_ME_COOKIE")
                .and()
             .rememberMe() // 자동로그인 설정
                .key("reco") // 쿠키값 암호화 복화화 기준키
                .tokenValiditySeconds(604800) //쿠키 유효기간 1주
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 로그인 처리를 하기 위한 AuthenticationManagerBuilder를 설정
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


}

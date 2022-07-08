package com.example.omymbackend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * packageName : com.example.taegyungsi.configuration
 * fileName : SecurityConfig
 * author : ds
 * date : 2022-06-20
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20         ds          최초 생성
 */
@EnableWebSecurity // 보안설정 사용 시 해당 어노테이션 적용
@RequiredArgsConstructor // final, @NotNull 붙은 변수만으로 생성자 생성
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // password 암호화 메소드
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // SpringBoot에서 제공하는 암호화 메소드
        return new BCryptPasswordEncoder();
    }
}

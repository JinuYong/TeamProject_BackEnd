package com.example.omymbackend.configuration;

import com.example.omymbackend.security.JwtAuthenticationFilter;
import com.example.omymbackend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * packageName : com.example.taegyungsi.configuration
 * fileName : SecurityConfig
 * author : hwan
 * date : 2022/06/20
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/20         hwan          최초 생성
 */
// @EnableWebSecurity : 보안 설정(JWT, SpringSecurity 등) 사용시 아래 어노테이션을 적용
@EnableWebSecurity
// @RequiredArgsConstructor : (Rombok) final, @NotNull 붙은 변수를 가진 생성자
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    웹토큰 생성, 인증 등 메소드를 위한 변수 정의
    public final JwtTokenProvider jwtTokenProvider;

//    패스워드 암호화 메소드
    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        springboot에서 제공하는 암호화 메소드
        return new BCryptPasswordEncoder();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    보안 설정 : 규칙 적용 ( 아래에서 이 사이트에 적용할 규칙을 정의 )
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                1) 기본 설정(세팅)
//                rest API 아니라면 기본 제공되는 로그인화면이 있음
                .httpBasic().disable() // rest API 기반이므로 비활성화
                .csrf().disable() // rest API csrf 보안이 필요없으므로 비활성화
//                기본 값 : 세션인증
//                JWT(웹토큰) 인증을 사용하므로 세션인증 사용안함(STATELESS)
//                세션인증 사용(SessionCreationPolicy.ALWAYS)
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//                2) 추가 설정 (권한 설정 : ROLE_USER, ROLE_ADMIN)
                .and()
                .authorizeRequests() // 화면을 접근할 수 있는 권한 설정 체크
//                아래 주소로 접근하면 모두 허용
                .antMatchers("/api/**").permitAll()
//                나머지 화면에 ROLE_USER 권한이 있는 사람만 접근
                .anyRequest().hasRole("USER")

//                3) 토큰을 필터로 끼워넣기
                .and()
                .addFilterBefore
                        (new JwtAuthenticationFilter(jwtTokenProvider),
                                UsernamePasswordAuthenticationFilter.class);
    }
}

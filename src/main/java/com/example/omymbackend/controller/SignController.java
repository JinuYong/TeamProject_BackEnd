package com.example.omymbackend.controller;

import com.example.omymbackend.model.JwtResponse;
import com.example.omymbackend.model.MessageResponse;
import com.example.omymbackend.model.User;
import com.example.omymbackend.security.JwtTokenProvider;
import com.example.omymbackend.service.CustomUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.example.taegyungsi.controller
 * fileName : SignController
 * author : hwan
 * date : 2022/06/20
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/20         hwan          최초 생성
 */
@RestController
@RequestMapping("/api/auth")
public class SignController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired : springboot의 객체를 하나 받아오기 (싱글톤 : 공유객체)
//               : null => 스프링객체
    @Autowired
    private CustomUserDetailService customUserDetailService;

//    패스워드 암호화 변수 (스프링부트에서 제공함)
    @Autowired
    private PasswordEncoder passwordEncoder;

//    웹토큰 변수 정의
    @Autowired
    private JwtTokenProvider jwtTokenProvider; // null => 스프링객체

//    로그인 메뉴 (sign in / log in)
    @PostMapping(value = "/signin")
    public ResponseEntity<?> signInUser(@RequestBody User user) {
//        임시 유저 객체를 정의 (이름으로 DB 유저정보 조회)
        User result = (User)customUserDetailService
                .findByName(user.getId());
//        암호 맞는지 확인 절차
//        user.getPassword() : 암호화 전 패스워드
//        result.getPassword() : DB에 저장된 패스워드(암호화가 된 패스워드)
//        암호가 틀리면 에러를 전송하고 함수를 종료
        if(!passwordEncoder
                .matches(user.getPassword(), result.getPassword())) {
//            Vue에 에러전송 (암호 틀리다고)
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse
                            ("Error: ID or Password is invalid."));
        }

//        밑으로 내려오면 암호가 같다는 의미
//        권한 체크( ROLE_USER )
//        User 안에 role : "ROLE_USER, ROLE_ADMIN, 등등"
        List<String> roleList = Arrays.asList(result.getRoles().split(","));

//        로그인 : 유저정보가 DB에 있는 것이 확인되면
//        JWT 토큰 생성해서 Vue에 전송
//        1) 토큰 생성
        String jwt = jwtTokenProvider.createToken(result.getSidx(), roleList);

//        권한이 여러개일 경우 아래 처리
//        map: 자동 for문을 호출( 함수형 프로그래밍에서 제공하는 메소드
        List<String> roles = result.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        logger.info("signUpRequest roles {}", roles);

//        2) 웹토큰(JWT) + 유저정보 Vue로 전송
        return ResponseEntity.ok(new JwtResponse(jwt,
                result.getIdx(),
                result.getId(),
                result.getEmail(),
                roles));
    }

//    회원가입 메뉴 (sign up)
    @PostMapping(value = "/signup")
    public ResponseEntity<?> addUser(HttpServletRequest request,
                                        @RequestBody User signupUser) {
//        임시 유저 객체 정의
        User user = signupUser;
//        유저에 role(역할) : ROLE_USER, 이 사이트는 롤이 1개임
        user.setRoles("ROLE_USER");
//        signupUser.getPassword() : 암호화되기전 패스워드
//        passwordEncoder.encode : 패스워드 암호화가 됨
        user.setPassword(passwordEncoder.encode(signupUser.getPassword()));

//        DB insert 할 서비스(메소드) 호출
        int result = customUserDetailService.signInUser(user);

//        DB insert 성공
        if( result == 1 ) {
//            성공메세지 전송
            return ResponseEntity
                    .ok(new MessageResponse("User registered successfully!"));
        } else if( result == -1) {
//            db에 유저가 있으므로 있다고 응답을 전송
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse
                            ("Error: Username is already token!"));
        } else {
//            DB 에러났으므로 관리자에게 문의하세요 응답 보냄
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse
                            ("Error: Ask system admin"));
        }
    }
}

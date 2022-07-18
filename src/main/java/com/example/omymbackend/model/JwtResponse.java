package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * packageName : com.example.taegyungsi.model
 * fileName : JwtResponse
 * author : hwan
 * date : 2022/06/17
 * description : 클라이언트로 응답을 보낼 클래스(객체)(웹토큰 포함)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/17         hwan          최초 생성
 */
@Setter
@Getter
@ToString
public class JwtResponse {
//    웹 토큰
    private String token;
    private String type = "Bearer"; // JWT(자바웹토큰)를 보낼때 사용될 타입
    private Long idx;
    private String username;
    private String email;
    private List<String> roles; // 역할 ( ROLE_ADMIN, ROLE_USER 등등 )

//    type을 제외한 모든 매개변수가 있는 생성자 정의
    public JwtResponse(String token, Long idx, String username, String email, List<String> roles) {
        this.token = token;
        this.idx = idx;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}

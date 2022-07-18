package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * packageName : com.example.omymbackend.model
 * fileName : SignIn
 * author : ds
 * date : 2022-07-18
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-18         ds          최초 생성
 */
@Getter
@Setter
@ToString
// UserDetails 내부적으로 Serialize를 상속받음 (네트웍전송 포맷)
// 인증 성공된 객체 : User
public class SignIn implements UserDetails {

    //    Serialize 네트웍 전송 자동 생성번호 => 수동으로 이름을 명시함
    //    보안관련해서 자동 번호생성
    private static final long serialVersionUID = 1L;

    private Long idx; // 시퀀스
    private String username;
    private String password;
    private String email;

    private String roles;
    private String profileUrl;
    private String deleteYn;
    private String registerTime;

    // TODO: idx : long -> string 변환
    private String sidx = String.valueOf(idx);

    //    권한 관련된 정보를 얻는 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        권한 정보를 담을 배열 정의
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
//        SimpleGrantedAuthority 권한 객체를 넣기 : auth 배열
        auth.add(new SimpleGrantedAuthority(roles));

        return auth;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

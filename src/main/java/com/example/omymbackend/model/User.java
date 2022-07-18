package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.omymbackend.model
 * fileName : User
 * author : ds
 * date : 2022-07-06
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06         ds          최초 생성
 */

@Getter
@Setter
@ToString
public class User {
    private Long idx; // 시퀀스
    private String id;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String postcode;
    private String address;
    private String detailAddress;
    private String roles;
    private String profileUrl;
    private String deleteYn;
    private String registerTime;

}

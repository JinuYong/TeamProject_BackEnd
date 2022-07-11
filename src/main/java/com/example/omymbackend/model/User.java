package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.omymbackend.model
 * fileName : User
 * author : gim-yeong-geun
 * date : 2022/07/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/07         gim-yeong-geun          최초 생성
 */
@Getter
@Setter
@ToString
public class User {
    private Long idx;
    private String id;
    private String email;
    private String name;
    private String phone;
    private String address;
    private String roles;
    private String profile;
    private int postcode;
    private String detailAddress;
}

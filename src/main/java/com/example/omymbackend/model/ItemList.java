package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemList {
//    Long(객체), long(일반자료형) => 8byte 정수형
    private int idx; // 상품번호 (sql 타입 = NUMBER)
    private String title; // 상품타이틀 (sql 타입 = VARCHAR2)
    private String content; // 상품 컨텐츠 (sql 타입 = VARCHAR2)
    private String insertTime; // 생성날짜 (sql 타입 = DATE)
    private String updateTime; // 업데이트날짜 (sql 타입 = DATE)
    private String deleteTime; // 삭제날짜 (sql 타입 = DATE)
    private String deleteYn; // 삭제여부 (sql 타입 = CHAR)
    private int price; // 가격 (sql 타입 = NUMBER)
    private int discountPer; // 할인 (sql 타입 = NUMBER)
    private int userCount; // 유저카운트 (sql 타입 = NUMBER)
    private String itemView; // 아이템뷰 (sql 타입 = DATE)
    private String itemDate; // 아이템날짜 (sql 타입 = DATE)
    private int itemCount; // 아이템카운트 (sql 타입 = NUMBER)
    private String departDate; // (sql 타입 = DATE)
    private int userIdx; // 유저번호 (sql 타입 = NUMBER)
    private String thumnail; // 썸네일 (sql 타입 = VARCHAR2)
    private String area; // 지역 카테고리 (sql 타입 = VARCHAR2)
    private int viewCount; // 상품조회 카운트 (sql 타입 = NUMBER)
    private int reviewCount; // 리뷰조회 카운트 (sql 타입 = NUMBER)

    // join
    private byte[] data;
    private String name;
}

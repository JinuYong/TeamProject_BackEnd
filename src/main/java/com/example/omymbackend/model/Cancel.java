package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.dongsungsi.model
 * fileName : Complain
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
@Getter
@Setter
@ToString
public class Cancel {
//    Long(객체), long(일반자료형) => 8byte 정수형
//    private Long no;
//    private String title;
//    private String content;
//    private String targetPage;
//    private String writer;
//    private String deleteYn;
//    private String insertTime;
//    private String updateTime;
//    private String deleteTime;

    private Long no;
    private String name;
    private String startDate;
    private String count;
    private String paymentDate;
    private String deleteYn;
    private String totalPrice;
    private String updateYn;


}












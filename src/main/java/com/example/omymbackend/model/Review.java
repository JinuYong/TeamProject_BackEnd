package com.example.omymbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Review {
//    Long(객체), long(일반자료형) => 8byte 정수형
    private Long idx;
    private String content;
    private Long userIdx;
    private Long rating;
    @JsonFormat(pattern="yyyy-MM-dd")
    private String insertTime;
    private Long itemIdx;
    private String deleteYn;
    private byte[] img;

    //join
    private String id;
}












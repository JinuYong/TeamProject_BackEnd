package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * packageName : com.example.omymbackend.model
 * fileName : review
 * author : gim-yeong-geun
 * date : 2022/07/12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/12         gim-yeong-geun          최초 생성
 */
@Getter
@Setter
@ToString
public class Review {

    private Long idx;
    private String content;
    private Long userIdx;
    private int rating;
    private Date insertTime;
    private Long itemIdx;
    private String deleteYn;
    private String img;

    //join
    private String id;
}

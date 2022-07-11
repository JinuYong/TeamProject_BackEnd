package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * packageName : com.example.omymbackend.model
 * fileName : Items
 * author : gim-yeong-geun
 * date : 2022/07/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/04         gim-yeong-geun          최초 생성
 */
@Getter
@Setter
@ToString
public class Cart {
    private Long idx;
    private Long userIdx;
    private Long itemsIdx;
    private int quntyty;

    // 조인해서 들고올꺼
    private String title;
    private String content;
    private int price;
    private int discountPer;
    private String itemDate;
    private String area;
}

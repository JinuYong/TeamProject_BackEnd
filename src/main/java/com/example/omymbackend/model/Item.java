package com.example.omymbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * packageName : com.example.omymbackend.model
 * fileName : Item
 * author : gim-yeong-geun
 * date : 2022/07/11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/11         gim-yeong-geun          최초 생성
 */
@Getter
@Setter
@ToString
public class Item {
        private Long idx;
        private String title;
        private String content;
        private int price;
        private int discountPer;
        private int userCount;
        private int itemView;

        @JsonFormat(pattern="yyyy-MM-dd") // date format 바꾸는거!!!!!
        private Date itemDate;
        private int itemCount;

        @JsonFormat(pattern="yyyy-MM-dd")
        private Date departDate;
        private Long userIdx;
        private String thumnail;
        private String area;
        private int viewCount;
        private int reviewCount;

        // join
        private byte[] data;
        private String name;

}

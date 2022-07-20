package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.omymbackend.model
 * fileName : BoardReply
 * author : macbook
 * date : 7/12/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/12/22         macbook          최초 생성
 */

@Getter
@Setter
@ToString
public class InformReply {
    private Long idx;
    private Long userIdx;
    private String content;
    private String insertTime;
    private String deleteTime;
    private Long informIdx;

    // join
    private String id;
    private String profileUrl;

}

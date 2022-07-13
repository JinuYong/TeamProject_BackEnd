package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * packageName : com.example.omymbackend.model
 * fileName : login
 * author : macbook
 * date : 6/27/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/27/22         macbook          최초 생성
 */

@Getter
@Setter
@ToString
public class Board {

    // BOARD
    private Long idx; // 게시글 번호
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String insertTime; // 게시글 작성 시간
    private String deleteYn; // 게시글 삭제 여부
    private Long userIdx; // 유저 번호
    private Long count; // 게시글 조회수

    // JOIN
    private String profile; // 게시글 이미지
    private String id; // USER 테이블의 ID가 AUTHOR로 대체

}

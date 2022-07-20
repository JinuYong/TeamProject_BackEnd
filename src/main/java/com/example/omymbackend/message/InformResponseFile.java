package com.example.omymbackend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName : com.example.dongsunguploaddb.message
 * fileName : ResponseFile
 * author : ds
 * date : 2022-05-31
 * description : Vue에 이미지를 담아서 보낼 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31         ds          최초 생성
 */
@Getter
@Setter
// @AllArgsConstructor : Lombok에서 제공하는 모든 매개변수를 가진 생성자
@AllArgsConstructor
public class InformResponseFile {
    //    TB_FILES테이블의 컬럼정보를 토대로 만든 클래스
//    이미지 정보
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String id;
    private Long idx; // 게시글 번호
    private Long count; // 게시글 조회수
    private int replyCount; // 게시글 조회수
    private String insertTime; // 게시글 작성 시간
    private String profileUrl; // 게시글 이미지
    private Long userIdx; // 유저 번호
    private String fileName; // 첨부파일 이름
    private String url;  // 첨부파일 주소 ( C:/work/upload )
    private String fileType; // 이미지 유형 ( 이미지, 텍스트 등 )
    private long fileData; // 이미지 크기
}










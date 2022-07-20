package com.example.omymbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.omymbackend.model
 * fileName : Inform
 * author : macbook
 * date : 7/14/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/14/22         macbook          최초 생성
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Inform {

    private Long idx;
    private String title;
    private String content;
    private String insertTime;
    private String deleteYn;
    private Long userIdx;
    private Long count;
    private Long replyCount; // 게시물 댓글 개수

    private String id;
    private String profileUrl;

    // UPLOAD
    private String fileId; // 첨부파일 id
    private String fileName; // 첨부파일 이름
    private String fileType; // 첨부파일 유형( image, text 등)
    private byte[] fileData; // oracle 이미지 담는 자료형(blob) : byte[]

    //    매개변수 3개짜리 생성자
    public Inform(String title, String content, Long userIdx, String fileName, String fileType, byte[] fileData) {
        this.title = title;
        this.content = content;
        this.userIdx = userIdx;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }

}

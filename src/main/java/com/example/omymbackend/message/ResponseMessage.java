package com.example.omymbackend.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.dongsunguploaddb.message
 * fileName : ResponseMessage
 * author : ds
 * date : 2022-05-31
 * description : Vue 응답 메세지를 담을 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31         ds          최초 생성
 */
@Setter
@Getter
@ToString
public class ResponseMessage {
//    메세지를 담을 변수
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
}












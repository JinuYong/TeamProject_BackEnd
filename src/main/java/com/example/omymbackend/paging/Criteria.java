package com.example.omymbackend.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.omymbackend.paging
 * fileName : criteria
 * author : macbook
 * date : 6/27/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/27/22         macbook          최초 생성
 */
@Setter
@Getter
@ToString
public class Criteria {
    private Integer page; // 현재 페이지 번호

    private Integer size; // 1페이지 당 출력할 데이터 개수

    private Integer totalItems; // 테이블 총 데이터 개수

    // 계산 공식 : 테이블 총 데이터 개수 / 1페이지 당 출력할 데이터 개수
    private Integer totalPages; // 총 페이지 수 (totalItems / size)

    private String title; // 검색 제목

    private String content;

    private String targetPage;

    private Long idx;

    private String id;

    // 기본 페이지 정보 저장
    public Criteria() {
        this.page = 0; // 현재 페이지 번호
        this.size = 10; // 페이지 당 데이터 건수
    }
}

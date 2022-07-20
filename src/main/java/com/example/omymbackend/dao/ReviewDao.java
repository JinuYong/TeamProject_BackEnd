package com.example.omymbackend.dao;

import com.example.omymbackend.model.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.dao
 * fileName : CustomerDao
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// mybatis 사용시 아래 어노테이션을 붙임
//    인터페이스 구현 => .xml에서 함(스프링에 자동 객체 생성)
@Mapper
public interface ReviewDao {
////    전체 회원 조회 메소드
//    List<Complain> findAll();

//    id로 회원 조회 메소드
    Optional<Review> findByIdx(Long idx);
//
//    회원 생성 메소드
    long insertReview(Review review);

//    회원 수정 메소드
//    long updateReview(Review review);
//
////    no로 회원 삭제 메소드
//    int deleteComplain(Long no);
////    전체 회원 삭제 메소드
//    int deleteAll();
List<Review> findByItemIdx(Long itemIdx);

}













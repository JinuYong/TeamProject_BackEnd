package com.example.omymbackend.dao;

import com.example.omymbackend.model.Cancel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
public interface CancelDao {
//    전체 회원 조회 메소드
    List<Cancel> findAll();

////    id로 회원 조회 메소드
//    Optional<Complain> findByNo(Long no);
//
////    회원 생성 메소드
//    long insertComplain(Complain complain);
//
////    no 리뷰 작성 메소드
    int updateReview(Long no);
//
//    no로 회원 삭제 메소드
    int deleteCancel(Long no);
////    전체 회원 삭제 메소드
//    int deleteAll();
}













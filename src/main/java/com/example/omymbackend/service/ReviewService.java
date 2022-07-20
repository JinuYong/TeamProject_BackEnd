package com.example.omymbackend.service;

import com.example.omymbackend.model.Review;

import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerService
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
public interface ReviewService {
//    id로 조회하는 메소드(결과 : 1건)
    Optional<Review> findByIdx(long idx);
//
////    모든 회원 조회하는 메소드(결과 : 여러건)
//    List<Board> findAll();

//    회원 저장 / 수정하는 메소드( insert / update )
    Optional<Review> save(Review review);

////    id로 회원 삭제하는 메소드
//    boolean deleteByNo(Long no);
//
////    모든 회원 삭제하는 메소드
//    boolean deleteAll();
}












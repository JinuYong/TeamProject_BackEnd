package com.example.omymbackend.controller;

import com.example.omymbackend.model.Review;
import com.example.omymbackend.service.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.customerspring.controller
 * fileName : CustomerController
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// 기본 보안 주소 허용 : http://localhost:8080
@CrossOrigin(origins = "http://localhost:3000")
// @RestController : 통신을 json형태로 주고받고 싶을때 사용
@RestController
// @RequestMapping("/api") : http://localhost:8000/api
@RequestMapping("/api")
public class ReviewController {

    //    logger 찍기를 위한 객체 만듬
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //  @Autowired : 스프링 객체 하나 받아오기
    @Autowired
    private ReviewServiceImpl reviewService; // 객체정의(null => 스프링객체)

//    //    모든 회원 조회 메뉴
//    @GetMapping("/boards")
//    public ResponseEntity<Object> getAllBoards() {
//
////        모든 회원 조회 서비스 호출
//        List<Board> boards = boardService.findAll();
//
//        try {
////            Vue에 성공메세지 + 객체를 전송
//            return new ResponseEntity<Object>(boards, HttpStatus.OK);
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
////            Vue 에 에러메세지 전송
//            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
//        }
//    }

    //    회원 생성 메뉴
    @PostMapping("/mypage")
    public ResponseEntity<Object>
    createBoard(@RequestBody Review review) {
        logger.info("review {}", review);
//        save 리턴값 Optional<Customer> => save(customer).get() 객체를 꺼냄
        Review savedReview = reviewService.save(review).get();
        logger.info("savedReview {}", savedReview);
        try {
//            Vue에 객체 + 성공메세지 전송
            return new ResponseEntity<Object>(savedReview, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);


        }
    }

//    //    id로 회원 조회 메뉴
//    @GetMapping("/boards/{no}")
//    public ResponseEntity<Object>
//    getBoardByNo(@PathVariable("no") Long no) {
////        id로 회원 조회 하는 서비스를 호출 ( Optional 떼내기 : get() )
//        Board board = boardService.findByNo(no).get();
//
//        try {
//            if (board != null) {
////                성공시 Vue에 객체 + 성공메세지 전송
//                return new ResponseEntity<Object>(board, HttpStatus.OK);
//            } else {
////                Vue에 데이터가 없을 경우 Not found 전송
//                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
//            }
//
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
////            Vue에 에러 메세지 전송
//            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
//        }
//    }

//    //    id 회원 수정 메뉴
////    Vue에서 전송 : url 매개변수 + 객체
////    @PathVariable : url 매개변수을 받는 어노테이션
////    @RequestBody : 객체를 받는 어노테이션
//    @PutMapping("/boards/{no}")
//    public ResponseEntity<Object> updateBoard(
//            @PathVariable("no") Long no,
//            @RequestBody Board board
//    ) {
//        try {
//
////            customer에 id값 저장
//            board.setNo(no);
////          save : id가 null일경우  insert , id가 null이 아닐경우 update
////            Optional<Customer> => Customer 객체를 꺼낼려면 get() 호출해야함
//            Board savedBoard = boardService.save(board).get();
////          Vue에 db에 저장된 객체 + 상태메세지를 전송(OK)
//            return new ResponseEntity<Object>(savedBoard, HttpStatus.OK);
//
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
////            Vue 에러메세지 전송
//            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    //    id로 회원 삭제 메뉴
////    /customers/deletion/{id}
//    @PutMapping("/boards/deletion/{no}")
//    public ResponseEntity<HttpStatus> deleteBoard(
//            @PathVariable("no") Long no
//    ) {
//        try {
////            id로 삭제 서비스를 호출(내부적으로 update문이 실행됨)
//            boardService.deleteByNo(no);
////            Vue에 성공메세지
//            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//// 웹애플리케이션 개발 : 클라이언트(Vue,React,HTML) <-> 서버(SpringBoot, Node)
//// Vue(클라이언트) 에 에러메세지를 전송
//            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/review/{itemIdx}")
    public ResponseEntity<List<Review>> findItemIdx(
            @PathVariable("itemIdx")Long itemIdx) {

        List<Review> reviews = reviewService.findItemIdx(itemIdx);

        logger.info("review: {}",reviews);

        try{
            if (reviews != null) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}












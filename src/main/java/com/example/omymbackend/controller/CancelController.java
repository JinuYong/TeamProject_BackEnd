package com.example.omymbackend.controller;

import com.example.omymbackend.model.Cancel;
import com.example.omymbackend.service.CancelServiceImpl;
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
public class CancelController {

    //    logger 찍기를 위한 객체 만듬
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //  @Autowired : 스프링 객체 하나 받아오기
    @Autowired
    private CancelServiceImpl cancelService; // 객체정의(null => 스프링객체)

    //    모든 회원 조회 메뉴
    @GetMapping("/mypage")
    public ResponseEntity<Object> getAllComplains() {

//        모든 회원 조회 서비스 호출
        List<Cancel> cancels = cancelService.findAll();

        try {
//            Vue에 성공메세지 + 객체를 전송
            return new ResponseEntity<Object>(cancels, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue 에 에러메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    //    no로 회원 삭제 메뉴
//    /customers/deletion/{id}
    @PutMapping("/mypage/deletion/{no}")
    public ResponseEntity<HttpStatus> deleteComplain(
            @PathVariable("no") Long no
    ) {
        try {
//            id로 삭제 서비스를 호출(내부적으로 update문이 실행됨)
            cancelService.deleteByNo(no);
//            Vue에 성공메세지
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
// 웹애플리케이션 개발 : 클라이언트(Vue,React,HTML) <-> 서버(SpringBoot, Node)
// Vue(클라이언트) 에 에러메세지를 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

    //    no로 리뷰 disabled
    @PutMapping("/mypage/update/{no}")
    public ResponseEntity<HttpStatus> updateReview(
            @PathVariable("no") Long no
    ) {
        try {
//            id로 삭제 서비스를 호출(내부적으로 update문이 실행됨)
            cancelService.updateReviewByNo(no);
//            Vue에 성공메세지
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
// 웹애플리케이션 개발 : 클라이언트(Vue,React,HTML) <-> 서버(SpringBoot, Node)
// Vue(클라이언트) 에 에러메세지를 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }


}












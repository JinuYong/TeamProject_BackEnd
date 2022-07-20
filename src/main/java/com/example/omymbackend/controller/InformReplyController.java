package com.example.omymbackend.controller;

import com.example.omymbackend.model.InformReply;
import com.example.omymbackend.service.InformReplyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : BoardReplyController
 * author : macbook
 * date : 7/12/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/12/22         macbook          최초 생성
 */

@CrossOrigin(origins="http://localhost:3000")
@RestController // @RestController : 통신을 json 형태로 주고받고 싶을 때 사용
@RequestMapping("/api")
public class InformReplyController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InformReplyServiceImpl informReplyService;

    // 모든 댓글 조회
    @GetMapping("/inform/detail/reply/{idx}")
    public ResponseEntity<Object> getAllInformReply(@PathVariable("idx") Long idx) {
        logger.info("idx {}", idx);
        try {
            List<InformReply> informReply = informReplyService.findReplyByIdx(idx);
            return new ResponseEntity<Object>(informReply, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 생성
    @PostMapping("/inform/detail/reply/write")
    public ResponseEntity<Object>
    insertInformReply(@RequestBody InformReply informReply) {
        // save 리턴값 Optional<Customer> => save(customer).get() 객체를 꺼냄
        logger.info("informReply {} ", informReply);

        try {
            List<InformReply> savedInformReply = informReplyService.save(informReply);

            return new ResponseEntity<Object>(savedInformReply, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 수정 (idx)
    // @PathVariable : url 매개변수를 받는 어노테이션
    // @RequestBody : 객체를 받는 어노테이션
    @PutMapping("/inform/detail/reply/update/{idx}")
    public ResponseEntity<Object> updateInformReply(@PathVariable("idx") Long idx,
                                                  @RequestBody InformReply informReply) {
        try {
            // boardReply 에 id 값 저장
            informReply.setIdx(idx);

            // save : id 가 null 일 경우 insert , id 가 null이 아닐경우 update
            // Optional<BoardReply> => BoardReply 객체를 꺼낼려면 get() 메소드를 호출해야함
            List<InformReply> savedInformReply = informReplyService.save(informReply);

            // Vue 에 DB에 저장된 객체 + 상태메세지를 전송(OK)
            return new ResponseEntity<Object>(savedInformReply, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // id 로 회원 삭제 메뉴
    @PutMapping("/inform/detail/reply/deletion/{idx}")
    public ResponseEntity<HttpStatus> deleteInformReply(@PathVariable("idx") Long idx) {
        try {
            // id 로 삭제 서비스를 호출(내부적으로 update 문이 실행됨)
            informReplyService.deleteInformReply(idx);
            // Vue 에 전송메세지 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // 웹 애플리케이션 개발 : 클라이언트(Vue,React,HTML) <-> 서버(SpringBoot, Node)
            // Vue(클라이언트) 에 에러메세지 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

        }
    }
}

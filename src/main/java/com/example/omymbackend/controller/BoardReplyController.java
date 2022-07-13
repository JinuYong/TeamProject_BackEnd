package com.example.omymbackend.controller;

import com.example.omymbackend.model.Board;
import com.example.omymbackend.model.BoardReply;
import com.example.omymbackend.service.BoardReplyServiceImpl;
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
public class BoardReplyController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoardReplyServiceImpl boardReplyService;

    // 모든 댓글 조회
    @GetMapping("/board/detail/reply")
    public ResponseEntity<Object> getAllBoardReply() {

        try {
            List<BoardReply> boardReply = boardReplyService.findAll();
            return new ResponseEntity<Object>(boardReply, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 생성
    @PostMapping("/board/detail/reply/write")
    public ResponseEntity<Object>
    insertBoardReply(@RequestBody BoardReply boardReply) {
        // save 리턴값 Optional<Customer> => save(customer).get() 객체를 꺼냄
        logger.info("boardReply {} ", boardReply);

        try {
            BoardReply savedBoardReply = boardReplyService.save(boardReply).get();

            return new ResponseEntity<Object>(savedBoardReply, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 수정 (idx)
    // @PathVariable : url 매개변수를 받는 어노테이션
    // @RequestBody : 객체를 받는 어노테이션
    @PutMapping("/board/detail/reply/update/{idx}")
    public ResponseEntity<Object> updateBoardReply(@PathVariable("idx") Long idx,
                                                  @RequestBody BoardReply boardReply) {
        try {
            // boardReply 에 id 값 저장
            boardReply.setIdx(idx);

            // save : id 가 null 일 경우 insert , id 가 null이 아닐경우 update
            // Optional<BoardReply> => BoardReply 객체를 꺼낼려면 get() 메소드를 호출해야함
            BoardReply savedBoardReply = boardReplyService.save(boardReply).get();

            // Vue 에 DB에 저장된 객체 + 상태메세지를 전송(OK)
            return new ResponseEntity<Object>(savedBoardReply, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // id 로 회원 삭제 메뉴
    @PutMapping("/board/detail/reply/deletion/{idx}")
    public ResponseEntity<HttpStatus> deleteBoardReply(@PathVariable("idx") Long idx) {
        try {
            // id 로 삭제 서비스를 호출(내부적으로 update 문이 실행됨)
            boardReplyService.deleteBoardReply(idx);
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

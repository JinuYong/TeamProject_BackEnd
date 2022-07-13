package com.example.omymbackend.controller;

import com.example.omymbackend.model.Board;
import com.example.omymbackend.paging.Criteria;
import com.example.omymbackend.service.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : BoardController
 * author : macbook
 * date : 7/6/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/6/22         macbook          최초 생성
 */

@CrossOrigin(origins="http://localhost:3000")
@RestController // @RestController : 통신을 json 형태로 주고받고 싶을 때 사용
@RequestMapping("/api")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardServiceImpl boardService;

    // (CRUD R - select) 전체 게시물 조회
    @GetMapping("/board/list")
    public ResponseEntity<Object> getAllBoards(Criteria criteria) {

        logger.info("criteria : {}", criteria);

        try {
//            List<Board> boards = boardService.findAll(criteria);
            List<Board> boards = boardService.findByTitle(criteria);
            if (boards.isEmpty()) {
                // Vue 성공메세지 + 객체를 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("boards", boards);
            // page : 현재 페이지
            response.put("currentPage", criteria.getPage());
            // totalItems : 총 데이터 건수
            response.put("totalItems", criteria.getTotalItems());
            // totalPages : 총 페이지 개수
            response.put("totalPages", criteria.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // (CRUD R - select) 제목 검색을 통한 조회
    @GetMapping("/board/list/{title}")
    public ResponseEntity<Map<String, Object>> getBoardByTitle(@PathVariable("title") Criteria criteria) {
        logger.info("criteria {}", criteria); // totalItems, totalPages = null

        try {
            List<Board> boards = boardService.findByTitle(criteria);

            if (boards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // totalItems, totalPages = 값이 있음
            logger.info("criteria {}", criteria);
			// Todo : Map에 넣어 객체 + 페이지 정보를 Vue 로 전송
            Map<String, Object> response = new HashMap<>();
            response.put("boards", boards);
            // page : 현재페이지
            response.put("currentPage", criteria.getPage());
            // totalItems : 총 데이터 건수
            response.put("totalItems", criteria.getTotalItems());
            // totalPages : 총 페이지 개수
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // (CRUD R - select) 게시물 작성자로 회원 조회 메뉴
    @GetMapping("/board/list/{id}")
    public ResponseEntity<Object> getBoardById(@PathVariable("id") Criteria criteria) {
        logger.info("criteria {}", criteria); // totalItems, totalPages = null

        try {
            List<Board> boards = boardService.findById(criteria);

            if (boards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // totalItems, totalPages = 값이 있음
            logger.info("criteria {}", criteria);
            // Todo : Map에 넣어 객체 + 페이지 정보를 Vue 로 전송
            Map<String, Object> response = new HashMap<>();
            response.put("boards", boards);
            // page : 현재페이지
            response.put("currentPage", criteria.getPage());
            // totalItems : 총 데이터 건수
            response.put("totalItems", criteria.getTotalItems());
            // totalPages : 총 페이지 개수
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // id 로 회원 조회 메뉴
    @GetMapping("/board/list/{idx}")
    public ResponseEntity<Object> getBoardDetailByIdx(@PathVariable("idx") Long idx) {

        try {
            Optional<Board> board = boardService.findByIdx(idx);

            if (board != null) {
                // 성공 시 Vue 에 객체 + 성공메세지 전송
                return new ResponseEntity<Object>(board, HttpStatus.OK);
            } else {
                // Vue 에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // (CRUD R - select) 게시물 번호로 회원 조회 메뉴
    @GetMapping("/board/detail/{idx}")
    public ResponseEntity<Object>
    getBoardByIdx(@PathVariable("idx") Long idx ) {

        try {
            Optional<Board> board = boardService.findByIdx(idx);

            if (board != null) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<Object>(board, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // (CRUD C - insert) 게시물 작성
    @PostMapping("/board/write")
    public ResponseEntity<Object> createBoard(@RequestBody Board board) {
        logger.info("board {}", board);

        try {
            Board savedBoard = boardService.save(board).get();
            return new ResponseEntity<>(savedBoard, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // (CRUD - U - update) 게시물 수정
    // @PathVariable : url 매개변수를 받는 어노테이션
    // @RequestBody : 객체를 받는 어노테이션
    @PutMapping("/board/detail/update/{idx}")
    public ResponseEntity<Object> updateBoard(@PathVariable("idx") Long idx,
            @RequestBody Board board) {

        try {
            // board 에 idx 값 저장
            board.setIdx(idx);
            // save : id 가 null 일 경우 insert , id 가 null이 아닐경우 update
            Board savedBoard = boardService.save(board).get();
            logger.info("savedBoard {}", savedBoard);
            // Vue 에 DB에 저장된 객체 + 상태메세지를 전송(OK)
            return new ResponseEntity<Object>(savedBoard, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // (CRUD - D - delete)
    @PutMapping("/board/deletion/{idx}")
    public ResponseEntity<HttpStatus> deleteBoardByIdx(@PathVariable("idx") Long idx) {

        try {
            // idx 로 삭제 서비스를 호출 (내부적으로 update 문이 실행됨)
            boardService.deleteByIdx(idx);
            // Vue 에 전송메세지 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // 웹 애플리케이션 개발 : 클라이언트(Vue,React,HTML) <-> 서버(SpringBoot, Node)
            // Vue(클라이언트) 에 에러메세지 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

    // (CRUD - D - delete)
    @PutMapping("/board/deletion/admin/")
    public ResponseEntity<HttpStatus> deleteBoardAll() {

        try {
            // idx 로 삭제 서비스를 호출 (내부적으로 update 문이 실행됨)
            boardService.deleteAll();
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

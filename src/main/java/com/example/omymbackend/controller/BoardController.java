package com.example.omymbackend.controller;

import com.example.omymbackend.message.BoardResponseFile;
import com.example.omymbackend.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @GetMapping("/board/list/id/{id}")
    public ResponseEntity<Map<String, Object>> getBoardById(@PathVariable("id") Criteria criteria) {
        logger.info("criteria ------ {}", criteria); // totalItems, totalPages = null

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

    // (CRUD R - select) 게시물 번호로 회원 조회 메뉴
    @GetMapping("/board/detail/{idx}")
    public ResponseEntity<Object>
    getBoardByIdx(@PathVariable("idx") Long idx ) {

        try {
            Optional<Board> board = boardService.findByIdx(idx);
            boardService.viewCount(idx);

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

    // id 로 회원 조회 메뉴
    @GetMapping("/board/list/idx/{idx}")
    public ResponseEntity<Object> getBoardDetailByIdx(@PathVariable("idx") Long idx) {

        try {
            Optional<Board> board = boardService.findByIdx(idx);
            boardService.viewCount(idx);

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

    //    이미지 업로드를 위한 메뉴(insert 호출)
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage>
    uploadFile(@RequestParam("boardTitle") String boardTitle,
               @RequestParam("content") String content,
               @RequestParam("userIdx") Long userIdx,
               @RequestParam("file") MultipartFile file) {
        String message = "";

        logger.info("boardTitle {}", boardTitle);
        logger.info("content {}", content);
        logger.info("userIdx {}", userIdx);
        logger.info("file {}", file);
        try {
            boardService.store(boardTitle, content, userIdx, file);
            message = "Uploaded the file successfully : "
                    + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "could not upload the file : "
                    + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage(message));
        }
    }

    //    파일 id를 조회해서 다운로드 하는 메뉴
    @GetMapping("/files/{fileId}")
    public ResponseEntity<byte[]>
    getFile(@PathVariable String fileId) {
        Optional<Board> board = boardService.getFile(fileId);

        return ResponseEntity.ok()
//                Todo : attachment: => attachment; 수정필요
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + board.get().getFileName() + "\"")
                .body(board.get().getFileData());
    }

    //    모든 이미지 목록을 조회하는 메뉴
    @GetMapping("/files")
    public ResponseEntity<List<BoardResponseFile>> getListFiles() {
        List<BoardResponseFile> files = boardService.getAllFile().map(dbFile -> {
            
            String fileDownloadURL = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/files/")
                    .path(dbFile.getFileId())
                    .toUriString();
            return new BoardResponseFile(
                    dbFile.getBoardTitle(),
                    dbFile.getContent(),
                    dbFile.getId(),
                    dbFile.getIdx(),
                    dbFile.getCount(),
                    dbFile.getInsertTime(),
                    dbFile.getProfileUrl(),
                    dbFile.getUserIdx(),
                    dbFile.getFileName(),
                    fileDownloadURL,
                    dbFile.getFileType(),
                    dbFile.getFileData().length);
        }).collect(Collectors.toList());

//        Vue 이미지 데이터 전송(여러개 이미지 파일 전송)
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    //    todo : 상세 이미지 정보를 조회하는 메뉴
    @GetMapping("/files/detail/{idx}")
    public ResponseEntity<BoardResponseFile> getDetailFiles(@PathVariable String idx) {
//        todo: getDetailFile 수정
        Optional<Board> board = boardService.getDetailFile(idx);

            String fileDownloadURL = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/files/")
                    .path(board.get().getFileId())
                    .toUriString();

        BoardResponseFile responseFile = new BoardResponseFile(
                    board.get().getBoardTitle(),
                    board.get().getContent(),
                    board.get().getId(),
                    board.get().getIdx(),
                    board.get().getCount(),
                    board.get().getInsertTime(),
                    board.get().getProfileUrl(),
                    board.get().getUserIdx(),
                    board.get().getFileName(),
                    fileDownloadURL,
                    board.get().getFileType(),
                    board.get().getFileData().length);

//        Vue 이미지 데이터 전송(여러개 이미지 파일 전송)
        return ResponseEntity.status(HttpStatus.OK).body(responseFile);
    }

    @RequestMapping(value="/boardDetailCount")
    public ModelAndView detailBoard(@RequestParam("idx") Long idx) throws Exception {

        // 기존의 게시글 자세히 보기에서 추가된 부분
        boardService.viewCount(idx);

        return new ModelAndView("detail","detail1",boardService.viewCount(idx));
    }

    @GetMapping("/board/detail/replyCount/{idx}")
    public ResponseEntity<Integer> getReplyCount(
            @PathVariable("idx") Long idx) {

        try {
            int board = boardService.viewReplyCount(idx);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

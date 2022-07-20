package com.example.omymbackend.controller;

import com.example.omymbackend.message.InformResponseFile;
import com.example.omymbackend.message.InformResponseFile2;
import com.example.omymbackend.message.ResponseMessage;
import com.example.omymbackend.model.Inform;
import com.example.omymbackend.service.InformServiceImpl;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : InformController
 * author : macbook
 * date : 7/14/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/14/22         macbook          최초 생성
 */

@CrossOrigin(origins="http://localhost:3000")
@RestController // @RestController : 통신을 json 형태로 주고받고 싶을 때 사용
@RequestMapping("/api")
public class InformController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InformServiceImpl informService;

    // 모든 여행정보 조회 서비스
    @GetMapping("/inform/list")
    public ResponseEntity<Object> getAllInforms() {

        try {
            List<Inform> informs = informService.findAll();

            return new ResponseEntity<Object>(informs, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 여행정보 제목으로 회원 조회 메뉴
    @GetMapping("/inform/list/{title}")
    public ResponseEntity<Object>
    getInformByTitle(@PathVariable("title") String title) {

        try {
            List<Inform> inform = informService.findInformByTitle(title);

            if (inform != null) {
                return new ResponseEntity<Object>(inform, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 여행정보 게시물번호(idx)로 회원 조회 메뉴
    @GetMapping("/inform/detail/{idx}")
    public ResponseEntity<Object> getInformByIdx(@PathVariable("idx") Long idx) {

        try {
            Optional<Inform> inform = informService.findInformByIdx(idx);
            informService.viewCount(idx);

            if (inform != null) {
                return new ResponseEntity<Object>(inform, HttpStatus.OK);
            } else {

                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 여행정보 게시물 생성
    @PostMapping("/inform/write")
    public ResponseEntity<Object> createInform(@RequestBody Inform inform) {

        try {
            Inform savedInform = informService.save(inform).get();

            return new ResponseEntity<Object>(savedInform, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 여행정보 게시물 수정
    @PutMapping("/inform/detail/update/{idx}")
    public ResponseEntity<Object> updateInform(@PathVariable("idx") Long idx,
                                              @RequestBody Inform inform) {

        try {
            inform.setIdx(idx);
            Inform savedInform = informService.save(inform).get();

            logger.info("savedInform {}", savedInform);
            return new ResponseEntity<Object>(savedInform, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 여행정보 게시물 삭제
    @PutMapping("/inform/deletion/{idx}")
    public ResponseEntity<HttpStatus> deleteInformByIdx(@PathVariable("idx") Long idx) {

        try {
            // idx 로 삭제 서비스를 호출 (내부적으로 update 문이 실행됨)
            informService.deleteInformByIdx(idx);
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
    @PostMapping("/inform/upload")
    public ResponseEntity<ResponseMessage>
    uploadFile(@RequestParam("title") String title,
               @RequestParam("content") String content,
               @RequestParam("userIdx") Long userIdx,
               @RequestParam("file") MultipartFile file) {
        String message = "";

        logger.info("title {}", title);
        logger.info("content {}", content);
        logger.info("userIdx {}", userIdx);
        logger.info("file {}", file);
        try {
            informService.store(title, content, userIdx, file);
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
    @GetMapping("/inform/files/{fileId}")
    public ResponseEntity<byte[]>
    getFile(@PathVariable String fileId) {
        Optional<Inform> inform = informService.getFile(fileId);

        return ResponseEntity.ok()
//                Todo : attachment: => attachment; 수정필요
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + inform.get().getFileName() + "\"")
                .body(inform.get().getFileData());
    }

    //    모든 이미지 목록을 조회하는 메뉴
    @GetMapping("/inform/topthree/all")
    public ResponseEntity<List<InformResponseFile2>> getListFiles2() {
        List<InformResponseFile2> files2 = informService.showTopThree().map(dbFile -> {

            // 기존거 새로 추가
            int replyCount = informService.viewReplyCount(dbFile.getIdx());

            String fileDownloadURL2 = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/inform/files/")
                    .path(dbFile.getFileId())
                    .toUriString();
            return new InformResponseFile2(
                    dbFile.getTitle(),
                    dbFile.getContent(),
                    dbFile.getId(),
                    dbFile.getIdx(),
                    dbFile.getCount(),
                    replyCount,
                    dbFile.getInsertTime(),
                    dbFile.getProfileUrl(),
                    dbFile.getUserIdx(),
                    dbFile.getFileName(),
                    fileDownloadURL2,
                    dbFile.getFileType(),
                    dbFile.getFileData().length);
        }).collect(Collectors.toList());

//        Vue 이미지 데이터 전송(여러개 이미지 파일 전송)
        return ResponseEntity.status(HttpStatus.OK).body(files2);
    }

    //    모든 이미지 목록을 조회하는 메뉴
    @GetMapping("/inform/files/all")
    public ResponseEntity<List<InformResponseFile>> getListFiles() {
        List<InformResponseFile> files = informService.getAllFile().map(dbFile -> {

            int replyCount = informService.viewReplyCount(dbFile.getIdx());

            String fileDownloadURL = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/inform/files/")
                    .path(dbFile.getFileId())
                    .toUriString();
            return new InformResponseFile(
                    dbFile.getTitle(),
                    dbFile.getContent(),
                    dbFile.getId(),
                    dbFile.getIdx(),
                    dbFile.getCount(),
                    replyCount,
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
    @GetMapping("/inform/files/detail/{idx}")
    public ResponseEntity<InformResponseFile> getDetailFiles(@PathVariable String idx) {
//        todo: getDetailFile 수정
        Optional<Inform> inform = informService.getDetailFile(idx);

        String fileDownloadURL2 = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/inform/files/")
                .path(inform.get().getFileId())
                .toUriString();

        InformResponseFile responseFile = new InformResponseFile(
                inform.get().getTitle(),
                inform.get().getContent(),
                inform.get().getId(),
                inform.get().getIdx(),
                inform.get().getCount(),
                0,
                inform.get().getInsertTime(),
                inform.get().getProfileUrl(),
                inform.get().getUserIdx(),
                inform.get().getFileName(),
                fileDownloadURL2,
                inform.get().getFileType(),
                inform.get().getFileData().length);

//        Vue 이미지 데이터 전송(여러개 이미지 파일 전송)
        return ResponseEntity.status(HttpStatus.OK).body(responseFile);
    }

    @RequestMapping(value="/informDetailCount")
    public ModelAndView detailInform(@RequestParam("idx") Long idx) throws Exception {

        // 기존의 게시글 자세히 보기에서 추가된 부분
        informService.viewCount(idx);

        return new ModelAndView("detail","detail1",informService.viewCount(idx));
    }

    @GetMapping("/inform/detail/replyCount/{idx}")
    public ResponseEntity<Integer> getReplyCount(
            @PathVariable("idx") Long idx) {

        try {
            int board = informService.viewReplyCount(idx);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

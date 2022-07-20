package com.example.omymbackend.controller;

import com.example.omymbackend.message.ResponseFile;
import com.example.omymbackend.message.ResponseMessage;
import com.example.omymbackend.model.FileDB;
import com.example.omymbackend.service.FileDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.example.dongsunguploaddb.controller
 * fileName : FileDBController
 * author : ds
 * date : 2022-05-31
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31         ds          최초 생성
 */
// 웹 브라우저 보안관련 : 프론트 8080포트로 오는 요청을 허용
// 참고사항 : ex)  기본 요청 : http://locahost:9000/board/list
//                  http://localhost:9000/api/list

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class FileDBController {

//  @Autowired : 스프링 객체를 받아옴
    @Autowired
    private FileDBService fileDBService; // 객체정의 ( null ) => 스프링객체 저장

//    로그를 출력위한 객체 생성
    Logger logger = LoggerFactory.getLogger(this.getClass());

//    이미지 업로드를 위한 메뉴(insert 호출)
//    Vue 에서 전송하는 형태(post)
//    @RequestParam : /upload
//    params = file
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage>
            uploadFile(@RequestParam("itemIdx") long itemIdx,
                    @RequestParam(value = "file") MultipartFile file) {
        String message = "";

//        매개변수 file 내용 출력
        logger.info("id {}", itemIdx);
        logger.info("file {}", file);

//        Vue에서 전송할 업로드 이미지를 DB에 저장(insert 문 호출)
        try {
            fileDBService.store(itemIdx, file);

            //            Vue에 보낼 메세지
            message = "Uploaded the file successfully : "
                    + file.getOriginalFilename();
//            html : 헤더 (상태정보, 문서정보), 바디(내용물)
//            성공메세지 전송
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(message));
        } catch (Exception e) {
//            Vue에 보낼 메세지
            message = "could not upload the file : "
                    + file.getOriginalFilename();
//            실패메세지 전송
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage(message));
        }
    }

//    파일 id를 조회해서 다운로드 하는 메뉴
//    @PathVariable : /files/{id} 일경우 사용하는 어노테이션
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]>
            getFile(@PathVariable String id) {
//        itemIdx로 조회하는 DB Select 문 호출
       FileDB fileDB = fileDBService.getFile(id);

//        첨부파일 다운로드 형태로 전송 : attachment: filename="image.jpg"
        return ResponseEntity.ok()
//                헤더정보(이미지이름) : attachment: filename fileDB.getName()
//                바디정보(이미지) : fileDB.getData()
//                Todo: attachment: => attachment;
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getItemFiles() {
        List<ResponseFile> files = fileDBService.getAllFile().map( dbFile -> {
            //        첨부파일을 다운로드할 위치 URL 만들어야 함
            //        Vue 클릭시 이미지 다운로드 가능
            //        URL 생성 : <img src="http://localhost:9000/api/files/1">
            String fileDownloadURL = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/files/")
                    .path(dbFile.getId())
                    .toUriString();

//            Vue 전송 ( 파일이름, url, 파일유형, 파일크기 )
//            이미지 파일 1개
            return new ResponseFile(
//                    Todo: dbFile.getId(), 추가
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadURL,
                    dbFile.getType(),
                    dbFile.getItemIdx(),
                    dbFile.getData().length);
//            stream => list
        }).collect(Collectors.toList());

//        Vue 이미지 데이터 전송(여러개 이미지 파일 전송)
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

////    모든 이미지 목록을 조회하는 메뉴
//    @GetMapping("/files")
//    public ResponseEntity<List<ResponseFile>> getListFiles() {
//        List<ResponseFile> files = fileDBService.getAllFile().map( dbFile -> {
//            //        첨부파일을 다운로드할 위치 URL 만들어야 함
//            //        Vue 클릭시 이미지 다운로드 가능
//            //        URL 생성 : <img src="http://localhost:9000/api/files/1">
//            String fileDownloadURL = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/api/files/")
//                    .path(dbFile.getId())
//                    .toUriString();
//
////            Vue 전송 ( 파일이름, url, 파일유형, 파일크기 )
////            이미지 파일 1개
//            return new ResponseFile(
////                    Todo: dbFile.getId(), 추가
//                    dbFile.getId(),
//                    dbFile.getName(),
//                    fileDownloadURL,
//                    dbFile.getType(),
//                    dbFile.getItemIdx(),
//                    dbFile.getData().length);
////            stream => list
//        }).collect(Collectors.toList());
//
////        Vue 이미지 데이터 전송(여러개 이미지 파일 전송)
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }
}













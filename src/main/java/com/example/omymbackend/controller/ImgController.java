package com.example.omymbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : ImgController
 * author : ds
 * date : 2022-07-15
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-15         ds          최초 생성
 */
@Slf4j
@CrossOrigin(value = "*", allowedHeaders = "*")
@RestController
public class ImgController {
//    @Autowired
//    ImgDao imgDao;

    ResourceLoader resourceLoader;

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping(value = "/profile/{profileUrl}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfileImg(@PathVariable("profileUrl") String imgUrl) throws IOException {
        log.info("imgUrl = ", imgUrl);
        InputStream imgStream = new FileInputStream(uploadPath + imgUrl);
        log.info("imgStream = ", imgStream);
        return null;
    }
}

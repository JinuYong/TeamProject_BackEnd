package com.example.omymbackend.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping("/image/{profileUrl}")
    public void getProfileImg(@PathVariable("profileUrl") String profileUrl, HttpServletResponse response) throws IOException {
        log.info("profileUrl = {}", profileUrl);
        Resource resource = new FileSystemResource(uploadPath + profileUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());

    }
}

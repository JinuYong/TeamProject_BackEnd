package com.example.omymbackend.controller;

import com.example.omymbackend.model.FileTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @PostMapping("/image/test")
    public void uploadTest(FileTest fileTest, @RequestParam(required = false, value = "file") MultipartFile file) {
        log.info("filetest = {}, file = {}", fileTest, file);
        log.info("title = {}, content = {}", fileTest.getTitle(), fileTest.getContent());
    }
}

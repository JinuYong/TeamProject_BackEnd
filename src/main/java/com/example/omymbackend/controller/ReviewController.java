package com.example.omymbackend.controller;

import com.example.omymbackend.model.Review;
import com.example.omymbackend.service.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : ReviewController
 * author : gim-yeong-geun
 * date : 2022/07/12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/12         gim-yeong-geun          최초 생성
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class ReviewController {

    @Autowired
    ReviewServiceImpl reviewService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/review/{itemIdx}")
    public ResponseEntity<List<Review>> findItemIdx(
                    @PathVariable("itemIdx")Long itemIdx) {

        List<Review> reviews = reviewService.findItemIdx(itemIdx);

        logger.info("review: {}",reviews);

        try{
            if (reviews != null) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

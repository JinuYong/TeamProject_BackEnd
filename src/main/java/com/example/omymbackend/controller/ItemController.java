package com.example.omymbackend.controller;

import com.example.omymbackend.model.Item;
import com.example.omymbackend.service.ItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : ItemController
 * author : gim-yeong-geun
 * date : 2022/07/11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/11         gim-yeong-geun          최초 생성
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class ItemController {

    @Autowired
    ItemServiceImpl itemService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/item/{idx}")
    public ResponseEntity<Object> findItemByIdx(
            @PathVariable("idx") Long idx) {


        Optional<Item> item = itemService.findItemByIdx(idx);
        logger.info("item : {}", item);

        try {
            if (item != null) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<>(item, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/item/area/{area}")
    public ResponseEntity<Object> findItemByArea(
            @PathVariable String area) {

        List<Item> areas = itemService.findItemByArea(area);
        logger.info("area : {}", areas);

        try {
            if (!areas.isEmpty()) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<>(areas, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

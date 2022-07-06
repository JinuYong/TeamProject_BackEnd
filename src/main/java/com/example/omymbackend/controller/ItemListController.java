package com.example.omymbackend.controller;

import com.example.omymbackend.model.ItemList;
import com.example.omymbackend.service.ItemListServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// cors 문제 ? 브라우저 보안상에 문제로 ip같은데 포트가 다를 경우 디폴트 통신 거절
//    허락을 구해야함 : @CrossOrigin(origins = "http://localhost:8080")
//    http://localhost:8080 : 프론트엔드의 포트번호를 지정
@CrossOrigin(origins = "http://localhost:3000")
// @RestController : REST API 호출을 위한 어노테이션 , JSON 형태로 들어옴
@RestController
@RequestMapping("/api")

public class ItemListController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ItemListServiceImpl itemListService; // 객체 정의 (null) => 스프링객체 받기

//    모든 지역 상품 조회
    @GetMapping("/itemlist/itemlist")
    public ResponseEntity<Object> getAllArea() {

//        모든 상품 조회 서비스 호출
        List<ItemList> itemLists = itemListService.findAll();

        try {
//            Vue에 객체 + 성공메세지 전송
            return new ResponseEntity<Object>(itemLists, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue에 에러메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

//    Area로 상품 조회
    @GetMapping("/itemlist/itemlist/{area}")
    public ResponseEntity<Object>
    getItemListByArea(@PathVariable("area") String area) {
//        Area로 상품 조회 하는 서비스 호출 ( Optional 떼내기 : .get() )
        ItemList itemList = itemListService.findByArea(area).get();

        try {
            if (itemList != null) {
//                성공시 프론트에 객체 + 성공메세지 전송
                return new ResponseEntity<Object>(itemList, HttpStatus.OK);
            } else {
//                프론트에 데이터가 없을 경우 NOT FOUND 전송
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            프론트에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

}

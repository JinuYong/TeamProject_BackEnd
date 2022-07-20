package com.example.omymbackend.controller;

import com.example.omymbackend.model.Item;
import com.example.omymbackend.paging.Criteria;
import com.example.omymbackend.service.ItemServiceImpl;
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

    @GetMapping("/items")
    public ResponseEntity<Object> findItemAll() {
        List<Item> items = itemService.findItemAll();

        try {
            logger.info("items: ", items);
            // 성공시
            if (!items.isEmpty()) {
                return new ResponseEntity<>(items, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

//    @GetMapping(value = "/item/date/{date}")
//    public ResponseEntity<Object> findSearch(@PathVariable String date) {
//        List<Item> search = itemService.findSearch(date);
//        logger.info("search: ", search);
//
//        try {
//            if (search.isEmpty()) {
////                조회 데이터가 없으면
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            // totalItems, totalPages = 값이 있음
//            logger.info("search {}", search);
//
////            조회 데이터가 있으면 : tutorials 전송, 상태정보 (OK ) 전송
//            return new ResponseEntity<>(search, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping(value = "/search")
//    public ResponseEntity<Object> findSearchData(
//            @RequestParam String area,
//            @RequestParam String itemDate) {
//
//        List<Item> search = itemService.findSearchData(area, itemDate);
//
//        try{
//            if(!search.isEmpty()){
//                return new ResponseEntity<>(search, HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping(value = "/search")
    public ResponseEntity<Map<String, Object>> findNavSearch(
            Criteria criteria) {

        logger.info("criteria(Vue에서 전송됨) {}", criteria);

        //        모든 회원 조회 서비스 호출 : findByEmailContaining
//        email 없으면 findAll , 있으면  findByEmailContaining
        List<Item> items
                = itemService.findNavSearch(criteria);
        logger.info("customers {}", items);

        try {
            if (items.isEmpty()) {
//                Vue로 내용물 없다고 전송(HttpStatus.NO_CONTENT)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("실행 후 : criteria {}", criteria);

//            Todo : Map 에 넣어 객체과 페이지정보를 Vue로 보냄
            Map<String, Object> response = new HashMap<>();
            response.put("items", items);
            response.put("currentPage", criteria.getPage());
            response.put("totalItems", criteria.getTotalItems());
            response.put("totalPages", criteria.getTotalPages());

//            Vue에 성공메세지 + 객체를 전송 + 페이지정보 전송
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue 에 에러메세지 전송
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

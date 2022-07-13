package com.example.omymbackend.controller;

import com.example.omymbackend.model.Cart;
import com.example.omymbackend.service.CartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : CartController
 * author : gim-yeong-geun
 * date : 2022/07/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/04         gim-yeong-geun          최초 생성
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class CartController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/cart/{userIdx}")
    public ResponseEntity<List<Cart>>
            findUserCart(@PathVariable("userIdx") Long userIdx) {
        // UserIdx로 회원 조회하는 서비스 불러오기

        List<Cart> carts = cartService.findUserCart(userIdx);

        logger.info("carts : {}", carts);

        try {
            if (!carts.isEmpty()) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<>(carts, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception ex) {
//            Vue에 에러 메세지 전송
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping ("/cart/{idx}")
    public ResponseEntity<Object> deleteUserCart(
            @PathVariable("idx") Long idx){
        try{
            boolean deleteUserCart =
                    cartService.deleteByCartItem(idx);
            if(deleteUserCart == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cart/{idx}")
    public ResponseEntity<Object> updateUserCart(
            @PathVariable("idx") Long idx,
            @RequestBody Cart cart){
        try {
            cart.setIdx(idx);
            List<Cart> updateCart = cartService.update(cart);
            return new ResponseEntity<>(updateCart, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cart")
    public ResponseEntity<Object> insertCartItem(
                @RequestBody Cart cart){
        logger.info("insertCart : cart {} : ", cart);

        // save 리턴값 Optional<Cart> => insertCartItem().get() 객체를 꺼냄
        Cart insertCartItem = cartService.insertCartItem(cart).get();

        try{
            return new ResponseEntity<>(insertCartItem, HttpStatus.OK);
        }catch (Exception e){
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

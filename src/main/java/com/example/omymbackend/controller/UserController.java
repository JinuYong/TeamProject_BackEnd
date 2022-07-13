package com.example.omymbackend.controller;

import com.example.omymbackend.model.Cart;
import com.example.omymbackend.model.User;
import com.example.omymbackend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : UserController
 * author : gim-yeong-geun
 * date : 2022/07/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/07         gim-yeong-geun          최초 생성
 */
@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user/{userIdx}")
    public ResponseEntity<Object> findByUserIdx(
            @PathVariable("userIdx") Long userIdx){

        Optional<User> user = userService.findByUserIdx(userIdx);

        try {
            if (user != null) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception ex) {
//            Vue에 에러 메세지 전송
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

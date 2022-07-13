package com.example.omymbackend.controller;

import com.example.omymbackend.model.User2;
import com.example.omymbackend.service.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : UserController2
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
@CrossOrigin("http://localhost:3000")
public class UserController2 {

    @Autowired
    UserServiceImpl2 userService;

    @GetMapping("/users/{userIdx}")
    public ResponseEntity<Object> findByUserIdx(
            @PathVariable("userIdx") Long userIdx){

        Optional<User2> user = userService.findByUserIdx(userIdx);

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

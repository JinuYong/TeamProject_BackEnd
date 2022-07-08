package com.example.omymbackend.controller;

import com.example.omymbackend.model.User;
import com.example.omymbackend.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * packageName : com.example.omymbackend.controller
 * fileName : SignUpController
 * author : ds
 * date : 2022-07-06
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06         ds          최초 생성
 */
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api")
@RestController
public class SignUpController {
    @Autowired
    SignupService signupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup/register")
    public void signup(@RequestParam("data")Object data) {
        log.info("data = " + data);
//        log.info(user.toString());
//        try {
//            boolean result = signupService.registerUser(user);
//            if (!result) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        catch (Exception e) {
//            log.error(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
    @PostMapping("/signup")
    public ResponseEntity idDuplicateConfirm(@RequestBody User user) {
        log.info("id = " + user.getId());
        try {
            boolean result = signupService.getById(user.getId());
            log.info("결과 = " + result);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}

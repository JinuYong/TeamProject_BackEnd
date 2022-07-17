package com.example.omymbackend.controller;

import com.example.omymbackend.model.User;
import com.example.omymbackend.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
@Slf4j
@CrossOrigin(value = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class SignUpController {
    @Autowired
    SignupService signupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${uploadPath}")
    private String uploadPath;

    @PostMapping("/signup")
    public ResponseEntity idDuplicateConfirm(@RequestBody User user) {
        log.info("id = " + user.getId());
        try {
            boolean result = signupService.compareId(user.getId());
            log.info("결과 = " + result);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/signup/register")
    public ResponseEntity signup(User user, @RequestParam(required = false, value = "profileFile")MultipartFile profileFile) throws IOException {
        log.info(user.toString());

        // 회원정보 db삽입
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            boolean result = signupService.registerUser(user, profileFile);
            if (!result) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/myinform/get")
    public ResponseEntity getUserInform(@RequestBody String userId) {
        String id = userId.substring(0, userId.length()-1);
        log.info(id);
        try {
            User user = signupService.getById(id);
            log.info(user.toString());
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/myinform/changepw")
    public ResponseEntity passwordChange(@RequestBody User user) {
        String userId = user.getId();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info(userId+"아이디" + user.getPassword() + "비밀번호");
        try {
            boolean result = signupService.passwordChange(user);
            if (result) {
                return ResponseEntity.ok(result);
            }
            else {
                return ResponseEntity.badRequest().body(result);
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/myinform/updateinform")
    public ResponseEntity putUserInform(User user, @RequestParam(required = false, value = "profileFile")MultipartFile profileFile) {
        log.info(user.toString());
        System.out.println(profileFile);

        try {
            boolean result = signupService.updateUserInform(user, profileFile);
            log.info("result = {}", result);
            if (!result) {
                return ResponseEntity.badRequest().body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/api")
@RestController
public class SignUpController {
    @Autowired
    SignupService signupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${uploadPath}")
    private String uploadPath;

    @PostMapping("/signup/register")
    public ResponseEntity signup(User user, @RequestParam("profileFile")MultipartFile profileFile) throws IOException {
        log.info(user.toString());

        if (profileFile.isEmpty()) {
            user.setProfileUrl("resources/user_profile/default.png");
        } else {
            // 파일
            String originalFilename = profileFile.getOriginalFilename();
            //확장자(EXT 추출하기)
            int pos = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(pos + 1);
            //UUID(랜덤한 중복될 가능성이 거의 없는 ID값) 생성 및 파일이름 부여
            String uuid = UUID.randomUUID().toString();
            //랜덤값 + 확장자
            String file_name = uuid + "." + ext;
            //파일저장 경로
            //fileDir => application.properties 에서 주입받은 값
            String file_path = uploadPath + file_name;
            //파일 저장 펑션 실행
            profileFile.transferTo(new File(file_path));
            user.setProfileUrl("resources/user_profile/" + file_name);

            System.out.println(file_name+ " / " +  file_path + " / " +originalFilename);
        }
        // 회원정보 db삽입
        try {
            boolean result = signupService.registerUser(user);
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
        String password = user.getPassword();
        log.info(userId+"아이디" + password + "비밀번호");
        try {
            boolean result = signupService.passwordChange(userId, password);
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

}

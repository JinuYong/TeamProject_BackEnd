package com.example.omymbackend.service;

import com.example.omymbackend.dao.UserDao;
import com.example.omymbackend.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * packageName : com.example.omymbackend.service
 * fileName : SignupServiceImpl
 * author : ds
 * date : 2022-07-06
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06         ds          최초 생성
 */
@Slf4j
@Service
public class SignupServiceImpl implements SignupService{
    @Autowired
    UserDao userDAO;

    @Value("${uploadPath}")
    private String uploadPath;

    @Override
    public boolean registerUser(User user, MultipartFile profileFile) throws IOException {
        log.info("profileFile = " + profileFile);

        if (profileFile == null) {
            user.setProfileUrl("default.png");
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
            user.setProfileUrl(file_name);

            System.out.println(file_name+ " / " +  file_path + " / " +originalFilename);
        }

        int result = userDAO.postUser(user);
        boolean boolResult = result > 0 ? true : false;
        log.info("인서트 결과  = " + boolResult);
        return boolResult;
    }
    @Override
    public boolean compareId(String id) {
        User result = userDAO.getById(id);
        boolean boolResult;
        if (result == null) {
            boolResult = true;
        } else boolResult = false;
        return boolResult;
    }

    @Override
    public User getById(String id) {
        User result = userDAO.getById(id);
        return result;
    }

    @Override
    public boolean passwordChange(User user) {
//        log.info("서비스 id = " + id + ", password = " + password);
        int result = userDAO.updatePassword(user);
        boolean boolResult = (result > 0 ? true : false);
        return boolResult;
    }

    @Override
    public boolean updateUserInform(User user, MultipartFile profileFile) throws IOException {
        log.info("signup Service 실행");
        log.info("user = {}", user);
        log.info("profileFile = {}", profileFile);
        if (profileFile != null) {
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

            // 기존파일 삭제
            File existFile = new File(uploadPath + user.getProfileUrl());
            log.info("existFile = {}", existFile);
            if (existFile.exists() && !existFile.equals("default.png")) {
                log.info("존재");
                existFile.delete();
                log.info("삭제");
            }
            // 파일명 저장
            user.setProfileUrl(file_name);
            log.info("file_name = {}", user.getProfileUrl());
        }

        int result = userDAO.updateUserInform(user);
        log.info("result = {}", result);
        boolean boolResult = result > 0 ? true : false;
        log.info("업데이트 결과 = {}", boolResult);
        return boolResult;
    }

    public User FindUserId(String name, String email) {
        log.info("username in service = {}", name);
        User user = userDAO.findUserId(name, email);
        return user;
    }

    @Override
    public User FindUserPassword(String id, String email) {
        User user = userDAO.findUserPassword(id, email);
        log.info("user in service = {}", user);
//        if (user.getId().equals(id) && user.getEmail().equals(email)) {
//            return user;
//        }
//        else return null;
        return user;
    }
}

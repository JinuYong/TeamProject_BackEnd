package com.example.omymbackend.service;

import com.example.omymbackend.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * packageName : com.example.omymbackend.service
 * fileName : SignupService
 * author : ds
 * date : 2022-07-06
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06         ds          최초 생성
 */
public interface SignupService {
    boolean registerUser(User user, MultipartFile profileFile)throws IOException ;

    boolean compareId(String id);

    User getById(String id);

    boolean passwordChange(User user);

    boolean updateUserInform(User user, MultipartFile profileFile)throws IOException;

    User FindUserId(String name, String email);

    User FindUserPassword(String id, String email);
}

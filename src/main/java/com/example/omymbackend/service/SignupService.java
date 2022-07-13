package com.example.omymbackend.service;

import com.example.omymbackend.model.User;

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
    boolean registerUser(User user);

    boolean compareId(String id);

    User getById(String id);

    boolean passwordChange(String id, String password);
}

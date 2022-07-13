package com.example.omymbackend.service;

import com.example.omymbackend.dao.UserDAO;
import com.example.omymbackend.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserDAO userDAO;

    @Override
    public boolean registerUser(User user) {
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
    public boolean passwordChange(String id, String password) {
        int result = userDAO.updatePassword(id, password);
        boolean boolResult = (result > 0 ? true : false);
        return boolResult;
    }
}

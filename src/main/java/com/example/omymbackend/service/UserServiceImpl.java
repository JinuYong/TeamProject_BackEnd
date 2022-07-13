package com.example.omymbackend.service;

import com.example.omymbackend.dao.UserDao;
import com.example.omymbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : com.example.omymbackend.service
 * fileName : UserServiceImpl
 * author : gim-yeong-geun
 * date : 2022/07/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/07         gim-yeong-geun          최초 생성
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public Optional<User> findByUserIdx(Long userIdx){

        return userDao.findByUserIdx(userIdx);
    }
}

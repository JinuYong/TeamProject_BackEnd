package com.example.omymbackend.service;

import com.example.omymbackend.dao.SignInDao;
import com.example.omymbackend.model.SignIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName : com.example.taegyungsi.service
 * fileName : CustomUserDetailService
 * author : hwan
 * date : 2022/06/20
 * description : UserDetails(User) : 유저의 정보를 얻기위한 서비스
 *                                  (DB : id, 이름, 이메일, 패스워드)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/20         hwan          최초 생성
 */
// @Service : springboot에 객체로 생성 ( ex) @Component, @Mapper )
@Service
public class CustomUserDetailService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SignInDao userDao; // 객체 정의 (null => 스프링객체 받기)

    @Override
    public UserDetails loadUserByUsername(String sidx)
            throws UsernameNotFoundException {
        return userDao.findByIdx(sidx);
    }

    public SignIn findByName(String name) {
        logger.info("username in detailService = {}", name);
        SignIn result = userDao.findByName(name);
        logger.info("service result = {}", result);
        return result;
    }

//    유저객체가 null이면 insert하고(유저생성),
//    아니면 -1 (개발자가 체크하기위해 임의로 주는) 반환하는 메소드
    public int signInUser(SignIn user) {
        if(userDao.findByName(user.getUsername()) == null) {
            return userDao.insertUser(user);
        } else {
            return -1;
        }
    }
}

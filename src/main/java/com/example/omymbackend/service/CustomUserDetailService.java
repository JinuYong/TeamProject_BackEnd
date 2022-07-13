package com.example.omymbackend.service;

import com.example.omymbackend.dao.UserDao;
import com.example.omymbackend.model.User;
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

    @Autowired
    UserDao userDao; // 객체 정의 (null => 스프링객체 받기)

    @Override
    public UserDetails loadUserByUsername(String sidx)
            throws UsernameNotFoundException {
        return userDao.findByIdx(sidx);
    }

    public UserDetails findByName(String name) {
        return userDao.findByName(name);
    }

//    유저객체가 null이면 insert하고(유저생성),
//    아니면 -1 (개발자가 체크하기위해 임의로 주는) 반환하는 메소드
    public int signInUser(User user) {
        if(userDao.findByName(user.getId()) == null) {
            return userDao.insertUser(user);
        } else {
            return -1;
        }
    }
}

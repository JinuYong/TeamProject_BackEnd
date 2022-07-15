package com.example.omymbackend.dao;

import com.example.omymbackend.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : UserDao
 * author : gim-yeong-geun
 * date : 2022/07/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/07         gim-yeong-geun          최초 생성
 */
@Mapper
public interface UserDao {

    // user 정보 불러오기
    Optional<User> findByUserIdx(Long userIdx);

    //    idx로 유저 가져오기 메소드
    User findByIdx(String sidx);

    //    name으로 유저 가져오기 메소드
    User findByName(String name);

    //    회원정보(user) insert 메소드
    int insertUser(User user);

    //    회원정보(user) delete 메소드
    int deleteUser(String name);

    int postUser(User user);

    User getById(String id);

    int updatePassword(String id, String password);
}
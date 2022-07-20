package com.example.omymbackend.dao;

import com.example.omymbackend.model.User;
import org.apache.ibatis.annotations.Mapper;

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
    int postUser(User user);

    User getById(String id);

    User getByIdx(Long idx);

    int updatePassword(User user);

    int updateUserInform(User user);

    User findUserId(String name, String email);

    User findUserPassword(String id, String email);
    
    int postUser(User user);

    User getById(String id);

    int updatePassword(String id, String password);
}

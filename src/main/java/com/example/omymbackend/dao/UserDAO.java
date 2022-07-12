package com.example.omymbackend.dao;

import com.example.omymbackend.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : UserDAO
 * author : ds
 * date : 2022-07-06
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06         ds          최초 생성
 */
@Mapper
public interface UserDAO {
    int postUser(User user);

    User getById(String id);
}

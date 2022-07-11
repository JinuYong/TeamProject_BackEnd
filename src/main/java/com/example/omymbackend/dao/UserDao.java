package com.example.omymbackend.dao;

import com.example.omymbackend.model.Cart;
import com.example.omymbackend.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

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
}

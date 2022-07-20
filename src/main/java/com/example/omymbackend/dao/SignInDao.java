package com.example.omymbackend.dao;

import com.example.omymbackend.model.SignIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : SignInDao
 * author : ds
 * date : 2022-07-18
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-18         ds          최초 생성
 */
@Mapper
public interface SignInDao {
        // user 정보 불러오기
        Optional<SignIn> findByUserIdx(Long userIdx);

        //    idx로 유저 가져오기 메소드
        SignIn findByIdx(String sidx);

        //    name으로 유저 가져오기 메소드
        SignIn findByName(String name);
        //    회원정보(user) insert 메소드
        int insertUser(SignIn user);

        //    회원정보(user) delete 메소드
        int deleteUser(String name);
}

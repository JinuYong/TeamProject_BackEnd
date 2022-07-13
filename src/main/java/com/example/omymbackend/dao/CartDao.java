package com.example.omymbackend.dao;

import com.example.omymbackend.model.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : ItemsDao
 * author : gim-yeong-geun
 * date : 2022/07/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/04         gim-yeong-geun          최초 생성
 */
@Mapper
public interface CartDao {

    // 유저의 카트 아이템 값 불러오기
    List<Cart> findUserCart(Long userIdx);

    // 카트 아이템 값 수정하기
    // 카트에서 수량을 선택하고 결제 페이지로 넘어갈때 저장
    long updateUserItem(Cart cart);

    // 유저의 카트 아이템 내역 삭제하기
    long deleteByCartItem(long userIdx);
}

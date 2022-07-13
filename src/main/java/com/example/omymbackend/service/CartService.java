package com.example.omymbackend.service;

import com.example.omymbackend.model.Cart;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.service
 * fileName : CartService
 * author : gim-yeong-geun
 * date : 2022/07/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/04         gim-yeong-geun          최초 생성
 */
public interface CartService {

    // 유저의 카트 아이템 값 불러오기
    List<Cart> findUserCart(long userIdx);

    // 카트 idx로 불러오기
    Optional<Cart> findCart(Long idx);

    // 카트 아이템 값 수정하기
    // 카트에서 수량을 선택하고 결제 페이지로 넘어갈때 저장
    List<Cart> update(Cart cart);

    // 유저의 카트 아이템 내역 삭제하기
    boolean deleteByCartItem(long userIdx);

    // 카트에 아이템 추가
    Optional<Cart> insertCartItem(Cart cart);
}

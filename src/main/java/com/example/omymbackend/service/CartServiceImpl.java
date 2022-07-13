package com.example.omymbackend.service;

import com.example.omymbackend.dao.CartDao;
import com.example.omymbackend.model.Cart;
import com.example.omymbackend.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    // 유저의 카트 아이템 값 불러오기
//    List<Cart> findUserCart(long userIdx);
    @Override
    public List<Cart> findUserCart(long userIdx) {
        return cartDao.findUserCart(userIdx);
    }
//
    @Override
    public Optional<Cart> findCart(Long idx) {
        return cartDao.findCart(idx);
    }

//    // 유저의 카트 아이템 내역 삭제하기
    @Override
    public boolean deleteByCartItem(long userIdx) {
        long queryResult = 0;

        queryResult = cartDao.deleteByCartItem(userIdx);

        return (queryResult >= 1) ? true : false;
    }

    @Override
    public List<Cart> update(Cart cart) {
        long seqIdx = 0;

        if(cart.getIdx() != null) {
            seqIdx = cartDao.updateUserItem(cart);
        }
        // insert 문 후에는 cart의 idx 속성값이 저장됨(<selelctkey>)
        seqIdx = cart.getIdx();
        return cartDao.findUserCart(seqIdx);
    }

    @Override
    public Optional<Cart> insertCartItem(Cart cart) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

        logger.info("cart {} : ", cart);

        if(cart.getIdx() == null) {
            seqId = cartDao.insertCartItem(cart);
        } else {
            seqId = cartDao.insertCartItem(cart);
        }

        // insert 문 후에는 cart의 id 속성값이 저장됨(<selectkey>)
        seqId = cart.getIdx();
        logger.info("seqId {}",seqId);

        return cartDao.findCart(seqId);
    }
}

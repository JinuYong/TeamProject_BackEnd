package com.example.omymbackend.service;

import com.example.omymbackend.dao.ItemListDao;
import com.example.omymbackend.model.ItemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemListServiceImpl implements ItemListService{

//    스프링에 ItemListDao 이미 생성 되어있음
//    생성된 객체를 받아오는 어노테이션 : @Autowired
    @Autowired
    private ItemListDao itemListDao; // 객체 정의 ( null => 스프링객체 )

// 전체 상품 조회 메소드
    @Override
    public List<ItemList> findAll() {
        return itemListDao.findAll();
    }

//  지역별 조회 (필터링) 메소드
    @Override
    public List<ItemList> findByArea(String area) {
        return itemListDao.findByArea(area);
    }

    @Override
    public Optional<ItemList> findByIdx(int idx) {
        return itemListDao.findByIdx(idx);
    }

    //    기타 상품 조회(필터링) 메소드
    @Override
    public List<ItemList> findByEtc() {
        return itemListDao.findByEtc();
    }

    //    가격낮은순 조회 메소드
    @Override
    public List<ItemList> findPrice() {
        return itemListDao.findPrice();
    }

//    조회순 조회 메소드
    @Override
    public List<ItemList> findViewCount() {
        return itemListDao.findViewCount();
    }

//  리뷰많은순 조회 메소드
    @Override
    public List<ItemList> findReviewCount() {
        return itemListDao.findReviewCount();
    }
}

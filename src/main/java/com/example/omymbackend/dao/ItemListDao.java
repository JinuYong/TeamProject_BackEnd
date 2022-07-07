package com.example.omymbackend.dao;

import com.example.omymbackend.model.ItemList;
import com.example.omymbackend.paging.criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

// mybatis 사용시 아래 어노테이션을 붙임
//    인터페이스 구현 => .xml에서 함(스프링에 자동 객체 생성)
@Mapper
public interface ItemListDao {
//    전체 아이템 리스트 조회 메소드
    List<ItemList> findAll();

//    area 로 상품 조회 메소드
    List<ItemList> findByArea(String area);

//    price 가격 조회순 정렬
    List<ItemList> findPrice();

//    viewCount 조회순 정렬
    List<ItemList> findViewCount();

//  reviewCount 조회순 정렬
    List<ItemList> findReviewCount();

}

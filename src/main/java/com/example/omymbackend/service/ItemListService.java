package com.example.omymbackend.service;

import com.example.omymbackend.model.ItemList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemListService {

//    모든 상품 조회하는 메소드 ( 결과 : 여러건(전체) )
    public List<ItemList> findAll();

//    Area 로 조회하는 메소드 ( 결과 : 1건 )
    public List<ItemList> findByArea(String area);

//    Price 가격낮은순 조회 메소드 ( 결과 : 여러건 )
    public List<ItemList> findPrice();

//    viewCount 조회순 조회 메소드 ( 결과 : 여러건 )
    List<ItemList> findViewCount();

//    reviewCount 리뷰많은순 조회 메소드 ( 결과 : 여러건 )
    List<ItemList> findReviewCount();


}

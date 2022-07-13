package com.example.omymbackend.dao;

import com.example.omymbackend.model.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : review
 * author : gim-yeong-geun
 * date : 2022/07/12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/12         gim-yeong-geun          최초 생성
 */
@Mapper
public interface ReviewDao {

    // 아이템 idx로 리뷰를 불러옴
    List<Review> findByItemIdx(Long itemIdx);
}

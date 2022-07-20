package com.example.omymbackend.dao;

import com.example.omymbackend.model.Item;
import com.example.omymbackend.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : ItemDao
 * author : gim-yeong-geun
 * date : 2022/07/11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/11         gim-yeong-geun          최초 생성
 */
@Mapper
public interface ItemDao {

    // findAll
    List<Item> findItemAll();

    // idx로 item 찾기
    Optional<Item> findItemByIdx(Long idx);

    // area로 item 찾기
    List<Item> findItemByArea(String area);

    // area, date 로 찾기
//    List<Item> findSearch(Item item);

    List<Item> findSearchData(String area, String itemDate);

    // keyword 로 검색시 총 건수 메소드
    int selectTotalCount(String keyword);

    List<Item> findNavSearch(Criteria criteria);

}

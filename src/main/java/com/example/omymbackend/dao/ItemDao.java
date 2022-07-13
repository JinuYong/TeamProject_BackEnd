package com.example.omymbackend.dao;

import com.example.omymbackend.model.Item;
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

    // idx로 item 찾기
    Optional<Item> findItemByIdx(Long idx);

    // area로 item 찾기
    List<Item> findItemByArea(String area);
}

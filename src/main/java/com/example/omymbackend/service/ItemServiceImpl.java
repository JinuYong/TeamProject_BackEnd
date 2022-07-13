package com.example.omymbackend.service;

import com.example.omymbackend.dao.ItemDao;
import com.example.omymbackend.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.service
 * fileName : ItemServiceImpl
 * author : gim-yeong-geun
 * date : 2022/07/11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/11         gim-yeong-geun          최초 생성
 */
@Service
public class ItemServiceImpl {

    @Autowired
    private ItemDao itemDao;

    public Optional<Item> findItemByIdx(Long idx) {
        return itemDao.findItemByIdx(idx);
    }

    public List<Item> findItemByArea(String area) {
        return itemDao.findItemByArea(area);
    }


}

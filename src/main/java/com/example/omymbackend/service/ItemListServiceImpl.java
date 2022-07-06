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

    @Override
    public List<ItemList> findAll() {
        return itemListDao.findAll();
    }


    @Override
    public List<ItemList> findByArea(String area) {
        return itemListDao.findByArea(area);
    }


}

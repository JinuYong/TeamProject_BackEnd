package com.example.omymbackend.service;

import com.example.omymbackend.dao.ItemDao;
import com.example.omymbackend.model.Item;
import com.example.omymbackend.paging.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public List<Item> findItemAll(){
        return itemDao.findItemAll();
    }

    public Optional<Item> findItemByIdx(Long idx) {
        return itemDao.findItemByIdx(idx);
    }

    public List<Item> findItemByArea(String area) {
        return itemDao.findItemByArea(area);

    }

    public List<Item> findSearchData(String area, String itemDate){
        return itemDao.findSearchData(area, itemDate);
    }

    public List<Item> findNavSearch(Criteria criteria){
        List<Item> items = Collections.emptyList();

        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getKeyword());

        int totalCount
                = itemDao.selectTotalCount(optionalCriteria.orElse(""));

//        Criteria 객체의 변수에 저장 : 페이지정보(totalCount, totalPages)
        criteria.setTotalItems(totalCount); // 테이블 총 건수 저장

//        totalPages(총페이지수) = totalCount(총건수) / size(1페이지당 총 건수)
        criteria.setTotalPages(totalCount / criteria.getSize());

        if(criteria.getKeyword() == null) {
//            email 없으면 전체검색(여러건 -> List)
            items = itemDao.findItemAll();

        } else {
//            email 있으면 email 검색(여러건 -> List)
            items = itemDao.findNavSearch(criteria);
        }

        return items;
    }



}

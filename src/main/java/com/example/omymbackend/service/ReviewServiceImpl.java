package com.example.omymbackend.service;

import com.example.omymbackend.dao.ReviewDao;
import com.example.omymbackend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : com.example.omymbackend.service
 * fileName : ReviewServiceImpl
 * author : gim-yeong-geun
 * date : 2022/07/12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/12         gim-yeong-geun          최초 생성
 */
@Service
public class ReviewServiceImpl {

    @Autowired
    private ReviewDao reviewDao;

    public List<Review> findItemIdx(Long itemIdx) {
        return reviewDao.findByItemIdx(itemIdx);
    }
}

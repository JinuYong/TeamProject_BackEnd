package com.example.omymbackend.dao;

import com.example.omymbackend.model.InformReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : BoardReplyDao
 * author : macbook
 * date : 7/12/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/12/22         macbook          최초 생성
 */
@Mapper
public interface InformReplyDao {

    List<InformReply> findReplyByIdx(Long idx);

    // CRUD - Create (insert)
    int insertInformReply(InformReply informReply);

    // CRUD - Update
    int updateInformReply(InformReply informReply);

    // CRUD - Delete
    int deleteInformReply(Long idx);
}

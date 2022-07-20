package com.example.omymbackend.dao;

import com.example.omymbackend.model.BoardReply;
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
public interface BoardReplyDao {

    List<BoardReply> findReplyByIdx(Long idx);

    // CRUD - Create (insert)
    int insertBoardReply(BoardReply boardReply);

    // CRUD - Update
    int updateBoardReply(BoardReply boardReply);

    // CRUD - Delete
    int deleteBoardReply(Long idx);

}

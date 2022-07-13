package com.example.omymbackend.service;

import com.example.omymbackend.model.Board;
import com.example.omymbackend.model.BoardReply;
import com.example.omymbackend.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.service
 * fileName : BoardReplyService
 * author : macbook
 * date : 7/12/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/12/22         macbook          최초 생성
 */
public interface BoardReplyService {

    // 댓글 전체 조회 (select)
    List<BoardReply> findAll();

    Optional<BoardReply> findReplyByIdx(Long idx);

    Optional<BoardReply> save(BoardReply boardReply);

    boolean deleteBoardReply(long idx);
}

package com.example.omymbackend.service;

import com.example.omymbackend.dao.BoardReplyDao;
import com.example.omymbackend.model.BoardReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.service
 * fileName : BoardReplyServiceImpl
 * author : macbook
 * date : 7/12/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/12/22         macbook          최초 생성
 */

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

    @Autowired
    private BoardReplyDao boardReplyDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BoardReply> findAll() {
        return boardReplyDao.findAll();
    }

    @Override
    public Optional<BoardReply> findReplyByIdx(Long idx) {
        return boardReplyDao.findReplyByIdx(idx);
    }

    @Override
    public Optional<BoardReply> save(BoardReply boardReply) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqIdx = 0;

        logger.info("boardReply {}", boardReply);

        if (boardReply.getIdx() == null) {
            boardReplyDao.insertBoardReply(boardReply);
        }
        else {
            boardReplyDao.updateBoardReply(boardReply);
        }

        // insert 문 후에는 board 의 id 속성에 값이 저장됨(<selectkey>)
        seqIdx = boardReply.getIdx();
        logger.info("seqIdx {}", seqIdx);

        return boardReplyDao.findReplyByIdx(seqIdx);
    }

    @Override
    public boolean deleteBoardReply(long idx) {
        int queryResult = 0;

        queryResult = boardReplyDao.deleteBoardReply(idx);

        return (queryResult >= 1) ? true : false;
    }
}

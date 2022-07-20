package com.example.omymbackend.service;

import com.example.omymbackend.dao.InformReplyDao;
import com.example.omymbackend.model.InformReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class InformReplyServiceImpl implements InformReplyService {

    @Autowired
    private InformReplyDao informReplyDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<InformReply> findReplyByIdx(Long idx) {
        return informReplyDao.findReplyByIdx(idx);
    }

    @Override
    public List<InformReply> save(InformReply informReply) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqIdx = 0;

        logger.info("informReply {}", informReply);

        if (informReply.getIdx() == null) {
            informReplyDao.insertInformReply(informReply);
        }
        else {
            informReplyDao.updateInformReply(informReply);
        }

        // insert 문 후에는 board 의 id 속성에 값이 저장됨(<selectkey>)
        seqIdx = informReply.getIdx();
        logger.info("seqIdx {}", seqIdx);

        return informReplyDao.findReplyByIdx(seqIdx);
    }

    @Override
    public boolean deleteInformReply(long idx) {
        int queryResult = 0;

        queryResult = informReplyDao.deleteInformReply(idx);

        return (queryResult >= 1) ? true : false;
    }
}

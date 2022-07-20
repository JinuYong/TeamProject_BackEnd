package com.example.omymbackend.service;

import com.example.omymbackend.model.InformReply;

import java.util.List;

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
public interface InformReplyService {

    List<InformReply> findReplyByIdx(Long idx);

    List<InformReply> save(InformReply informReply);

    boolean deleteInformReply(long idx);
}

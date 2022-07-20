package com.example.omymbackend.service;

import com.example.omymbackend.dao.CancelDao;
import com.example.omymbackend.model.Cancel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// @Service : 스프링에 객체로 생성
@Service
public class CancelServiceImpl implements CancelService {

//    스프링에 customerDao 이미 생성되어 있음
//    생성된 객체를 받아오는 어노테이션 : @Autowired
    @Autowired
    private CancelDao cancelDao; // 객체 정의 ( null => 스프링 객체 )

//    나중에 에러 로그 추적을 위해 하나 만들어둠
    Logger logger = LoggerFactory.getLogger(this.getClass());

//    id로 회원 조회 하는 메소드
//    @Override
//    public Optional<Complain> findByNo(long no) {
//        return complainDao.findByNo(no);
//    }

//    모든 회원 조회 서비스
    @Override
    public List<Cancel> findAll() {
        return cancelDao.findAll();
    }

//    회원 생성 / 수정 서비스 ( insert / update )
//    @Override
//    public Optional<Complain> save(Complain complain) {
////        db 시퀀스 번호 저장을 위한 변수
//        long seqNo = 0;
//
////        매개변수 customer 정보 출력
//        logger.info("complain {}", complain);
//
////        complain.getId() 없으면 insert 문 호출( 새로운 회원 생성 )
//        if(complain.getNo() == null) {
//            complainDao.insertComplain(complain);
//        }
//        //                         있으면 update 문 호출
//        else {
//            complainDao.updateComplain(complain);
//        }
//
////        insert 문 후에는 customer의 id속성에 값이 저장됨(<selectkey>)
//        seqNo = complain.getNo();
//        logger.info("seqNo {}", seqNo);
//
//        return complainDao.findByNo(seqNo);
//    }
    @Override
    public boolean deleteByNo(Long no) {
        int queryResult = 0;

        queryResult = cancelDao.deleteCancel(no);

        return (queryResult >=1) ? true : false;
    }

    @Override
    public boolean updateReviewByNo(Long no) {
        int queryResult = 0;

        queryResult = cancelDao.updateReview(no);

        return (queryResult >=1) ? true : false;
    }
//
//    @Override
//    public boolean deleteAll() {
//        int queryResult = 0;
//
////        delete / update / insert 문은 결과 실행 후
////        정상 실행되면 건수(삭제/수정/생성)가 결과로 리턴(반환)
//        queryResult = complainDao.deleteAll();
//
//        return (queryResult >=1) ? true : false;
//    }
}












package com.example.omymbackend.service;

import com.example.omymbackend.dao.BoardDao;
import com.example.omymbackend.model.Board;
import com.example.omymbackend.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.service
 * fileName : BoardServiceImpl
 * author : macbook
 * date : 7/6/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/6/22         macbook          최초 생성
 */

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 전체 조회
    @Override
    public List<Board> findAll(Criteria criteria) {
        return boardDao.findAll(criteria);
    }

    @Override
    public Optional<Board> findByIdx(Long idx) {
        return boardDao.findByIdx(idx);
    }

    @Override
    // 작성자 조회
    public List<Board> findById(Criteria criteria) {
        // 빈 값으로 초기화
        List<Board> boards = Collections.emptyList();

        // 제목이 Null 인지 체크
        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getId());

        // 페이징 처리 파트 시작
        // 테이블의 총 데이터 건수
        // Null 이면 "" 로 바꿈
        int totalCount = boardDao.selectTotalCount(optionalCriteria.orElse(""));

        // criteria : 페이징 처리 클래스 객체
        criteria.setTotalItems(totalCount);
        // 총 페이지 개수 : 테이블의 총 건수(totalCount) / 페이지당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());
        // 페이징 처리 파트 끝

        if(criteria.getId() == null){
            // Id(작성자)가 없으면 전체 검색
            boards = boardDao.findAll(criteria);
        } else {
            // Id(작성자)가 있으면 작성자 검색
            boards = boardDao.findById(criteria);
        }
        return boards;
    }

    // 제목 조회
    @Override
    public List<Board> findByTitle(Criteria criteria) {
        // 빈 값으로 초기화
        List<Board> boards = Collections.emptyList();

        // 제목이 Null 인지 체크
        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getTitle());

        // 페이징 처리 파트 시작
//         테이블의 총 데이터 건수
        // Null 이면 "" 로 바꿈
        int totalCount = boardDao.selectTotalCount(optionalCriteria.orElse(""));

        // criteria : 페이징 처리 클래스 객체
        criteria.setTotalItems(totalCount);
        // 총 페이지 개수 : 테이블의 총 건수(totalCount) / 페이지당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());
        // 페이징 처리 파트 끝

        if(criteria.getTitle() == null){
            // title(제목)이 없으면 전채검색을 함
            boards = boardDao.findAll(criteria);
        } else {
            // title(제목)이 있으면 제목 검색을 함
            boards = boardDao.findByTitle(criteria);
        }
        return boards;
    }

    // 게시판 글쓰기 및 수정
    @Override
    public Optional<Board> save(Board board) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqIdx = 0;

        // 매개변수 customer 정보 출력
        logger.info("board {}", board);

        // board.getId() 없으면 insert 문 호출(새로운 게시물 생성)
        if (board.getIdx() == null) {
            boardDao.insertBoard(board);
        }
        // 있으면 update 문 호출
        else {
            boardDao.updateBoard(board);
        }

        // insert 문 후에는 board 의 id 속성에 값이 저장됨(<selectkey>)
        seqIdx = board.getIdx();
        logger.info("seqIdx {}", seqIdx);

        return boardDao.findByIdx(seqIdx);
    }

    // IDX 해당하는 값 삭제
    @Override
    public boolean deleteByIdx(Long idx) {
        int queryResult = 0;

        queryResult = boardDao.deleteBoard(idx);

        return (queryResult >= 1) ? true : false;
    }

    // 전체 게시물 값 삭제
    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        // delete / update / insert 문은 결과 실행 후
        // 정상 실행되면 건수(삭제/수정/생성)
        queryResult = boardDao.deleteAll();

        return (queryResult >= 1) ? true : false;
    }
}

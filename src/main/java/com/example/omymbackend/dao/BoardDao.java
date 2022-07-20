package com.example.omymbackend.dao;

import com.example.omymbackend.model.Board;
import com.example.omymbackend.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : loginDao
 * author : macbook
 * date : 6/27/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/27/22         macbook          최초 생성
 */
@Mapper
public interface BoardDao {

    // CRUD - Read (select)
    // 전체 게시물 조회
    List<Board> findAll(Criteria criteria);

    // 게시물 제목으로 검색해서 조회
    List<Board> findByTitle(Criteria criteria);

    // 게시물 작성자 해당하는 값을 조회
    List<Board> findById(Criteria criteria);

    // 게시물 제목 데이터 건수를 세는 메소드
    int selectTotalCount(String boardTitle);

    int selectTotalIdCount(String id);

    // Users 테이블의 idx
    Optional<Board> findByIdx(Long idx);

    // CRUD - Create (insert)
    int insertBoard(Board board);

    // CRUD - Update
    int updateBoard(Board board);

    // CRUD - Delete
    int deleteBoard(Long idx);

    int deleteAll();

    int viewCount(Long idx);

    int viewReplyCount(Long idx);

    //    id로 이미지를 찾는 메소드(1건)
    Optional<Board> findByFileId(String fileId);

//    todo: 추가
    Optional<Board> findDetail(String idx);

    //    모든 이미지 목록을 찾는 메소드(여러건)
    List<Board> findAllFile();

    //    업로드 이미지 저장 메소드
    int saveFile(Board board);
}

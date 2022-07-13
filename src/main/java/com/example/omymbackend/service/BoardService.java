package com.example.omymbackend.service;

import com.example.omymbackend.model.Board;
import com.example.omymbackend.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.service
 * fileName : BoardService
 * author : macbook
 * date : 7/6/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/6/22         macbook          최초 생성
 */
public interface BoardService {

    // CRUD - Read
    // Select (전체 조회)
    List<Board> findAll(Criteria criteria);

    // Select (제목 검색 조회)
    List<Board> findByTitle(Criteria criteria);

    // Select (작성자 검색 조회)
    List<Board> findById(Criteria criteria);

    Optional<Board> findByIdx(Long idx);

    // CRUD - Create, Update
    // insert 및 update
    Optional<Board> save(Board board);

    // CRUD - Delete
    boolean deleteByIdx(Long idx);

    boolean deleteAll();
}

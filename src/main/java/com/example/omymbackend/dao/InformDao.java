package com.example.omymbackend.dao;

import com.example.omymbackend.model.Inform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.omymbackend.dao
 * fileName : InformDao
 * author : macbook
 * date : 7/14/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/14/22         macbook          최초 생성
 */

@Mapper
public interface InformDao {

    // 여행정보 전체 조회
    List<Inform> findAll();

    // 여행정보 제목으로 검색해서 조회
    List<Inform> findInformByTitle(String title);

    // 여행정보 idx
    Optional<Inform> findInformByIdx(Long idx);

    // CRUD - Create (insert)
    Long insertInform(Inform inform);

    // CRUD - Update
    Long updateInform(Inform inform);

    // CRUD - Delete
    int deleteInform(Long idx);

    int viewCount(Long idx);

    int viewReplyCount(Long idx);

    //    id로 이미지를 찾는 메소드(1건)
    Optional<Inform> findByFileId(String fileId);

    //    todo: 추가
    Optional<Inform> findInformDetail(String idx);

    // 모든 이미지 목록을 찾는 메소드(여러건)
    List<Inform> findAllFile();

    List<Inform> showTopThree();

    //    업로드 이미지 저장 메소드
    int saveFile(Inform inform);
}

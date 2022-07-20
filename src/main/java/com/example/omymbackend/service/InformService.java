package com.example.omymbackend.service;

import com.example.omymbackend.model.Inform;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * packageName : com.example.omymbackend.service
 * fileName : InformService
 * author : macbook
 * date : 7/14/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/14/22         macbook          최초 생성
 */
public interface InformService {

    List<Inform> findAll();

    List<Inform> findInformByTitle(String title);

    Optional<Inform> findInformByIdx(Long idx);

    Optional<Inform> save(Inform inform);

    boolean deleteInformByIdx(Long idx);

    int viewCount(Long idx);

    int viewReplyCount(Long idx);

    // 이미지 업로드
    int store(String title, String content, Long userIdx, MultipartFile file) throws IOException;

    Optional<Inform> getFile(String fileId);

    //    todo: 추가
    Optional<Inform> getDetailFile(String idx);

    Stream<Inform> getAllFile();

    Stream<Inform> showTopThree();

}

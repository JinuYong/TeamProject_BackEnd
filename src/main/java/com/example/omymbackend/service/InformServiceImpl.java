package com.example.omymbackend.service;

import com.example.omymbackend.dao.InformDao;
import com.example.omymbackend.model.Inform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * packageName : com.example.omymbackend.service
 * fileName : InformServiceImpl
 * author : macbook
 * date : 7/14/22
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 7/14/22         macbook          최초 생성
 */
@Service
public class InformServiceImpl implements InformService {

    @Autowired
    private InformDao informDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 여행정보 전체 조회
    @Override
    public List<Inform> findAll() {
        return informDao.findAll();
    }

    // 여행정보 제목 검색
    @Override
    public List<Inform> findInformByTitle(String title) {
        return informDao.findInformByTitle(title);
    }

    // 여행정보 IDX
    @Override
    public Optional<Inform> findInformByIdx(Long idx) {
        return informDao.findInformByIdx(idx);
    }

    // 여행정보 작성 및 수정
    @Override
    public Optional<Inform> save(Inform inform) {
        long seqIdx = 0;

        logger.info("inform {}", inform);

        if (inform.getIdx() == null) {
            informDao.insertInform(inform);
        } else {
            informDao.updateInform(inform);
        }

        seqIdx = inform.getIdx();
        logger.info("seqIdx {}", seqIdx);

        return informDao.findInformByIdx(seqIdx);
    }

    // 여행정보 삭제
    @Override
    public boolean deleteInformByIdx(Long idx) {
        int queryResult = 0;

        queryResult = informDao.deleteInform(idx);

        return (queryResult >= 1) ? true : false;
    }

    @Override
    public int viewCount(Long idx) {
        return informDao.viewCount(idx);
    }

    @Override
    public int store(String title, String content, Long userIdx, MultipartFile file) throws IOException {
        // 순수 이름 얻어짐
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

//        이미지 파일이 생성됨
        Inform inform = new
                Inform(title, content, userIdx, fileName, file.getContentType(), file.getBytes());

        // DB에 INSERT문 호출
        return informDao.saveFile(inform);
    }

    @Override
    public Optional<Inform> getFile(String fileId) {
        return informDao.findByFileId(fileId);
    }

    //    todo : 추가
    @Override
    public Optional<Inform> getDetailFile(String idx) {
        return informDao.findInformDetail(idx);
    }

    @Override
    public Stream<Inform> getAllFile() {
        Stream<Inform> resInform = informDao.findAllFile().stream();

        return resInform;
    }

    @Override
    public Stream<Inform> showTopThree() {
        Stream<Inform> resInform2 = informDao.showTopThree().stream();

        return resInform2;
    }

    @Override
    public int viewReplyCount(Long idx) {
        return informDao.viewReplyCount(idx);
    }
}
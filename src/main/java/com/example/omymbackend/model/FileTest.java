package com.example.omymbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.omymbackend.model
 * fileName : FileTest
 * author : ds
 * date : 2022-07-20
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-20         ds          최초 생성
 */
@Getter @Setter
@ToString
public class FileTest {
    private String title;
    private String content;
    private byte[] data;
}

package com.project.cafe.board.vo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity(name = "board")
@DynamicInsert
public class BoardVO {

    @Id
    private int articleNo;
    private String title;

    private String content;

    private String writer;

    private int hit; // 조회수

    private Date regDate;


}

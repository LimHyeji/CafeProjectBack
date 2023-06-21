package com.project.cafe.board.model.vo;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "board")
@DynamicInsert
public class BoardVO {

    @Id
    @Column(name = "articleNo")
    private int articleNo;
    @Column(name = "title",length = 100)
    @ColumnDefault("'noTitle'")
    private String title;

    @Column(name = "content",length = 2000)
    @ColumnDefault("'noContent'")
    private String content;

    @Column(name = "writer",length = 20)
    private String writer;

    @Column(name = "hit",nullable = false)
    @ColumnDefault("0")
    private int hit; // 조회수

    @Column(name = "regDate",nullable = false)
    @CreationTimestamp
    private Timestamp regDate;


    public BoardVO() {
    }

    public BoardVO(int articleNo, String title, String content, String writer, int hit, Timestamp regDate) {
        setArticleNo(articleNo);
        setTitle(title);
        setContent(content);
        setWriter(writer);
        setHit(hit);
        setRegDate(regDate);
    }

    public int getArticleNo() {
        return articleNo;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public int getHit() {
        return hit;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public void setTitle(String title) {
        if(title == null || title.trim().length() == 0) return; // throw error, 제목을 입력해주세요.
        this.title = title;
    }

    public void setContent(String content) {
        if(content == null || content.trim().length() == 0) return; // throw error, 내용을 입력해주세요.
        this.content = content;
    }

    public void setWriter(String writer) {
        if(writer == null || writer.trim().length() == 0) return; // throw error, 잘못된 요청입니다.
        this.writer = writer;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
}

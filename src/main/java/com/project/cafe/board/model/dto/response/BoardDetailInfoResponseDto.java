package com.project.cafe.board.model.dto.response;

import java.sql.Timestamp;

public class BoardDetailInfoResponseDto {

    private int articleNo;
    private String title;

    private String content;

    private String writer;
    private int hit; // 조회수
    private Timestamp regDate;


    public BoardDetailInfoResponseDto() {
    }

    public BoardDetailInfoResponseDto(int articleNo, String title, String content, String writer, int hit, Timestamp regDate) {
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

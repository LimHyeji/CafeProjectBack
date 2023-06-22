package com.project.cafe.board.model.dto.response;

import com.project.cafe.board.model.vo.BoardVO;

import java.sql.Timestamp;

public class BoardSimpleInfoResponseDto {

    private int articleNo;
    private String title;
    private String writer;
    private int hit; // 조회수
    private Timestamp regDate;

    public BoardSimpleInfoResponseDto() {
    }

    public BoardSimpleInfoResponseDto(int articleNo, String title, String writer, int hit, Timestamp regDate) {
        setArticleNo(articleNo);
        setTitle(title);
        setWriter(writer);
        setHit(hit);
        setRegDate(regDate);
    }

    public void convertToDto(BoardVO board) {
        setArticleNo(board.getArticleNo());
        setTitle(board.getTitle());
        setWriter(board.getWriter());
        setHit(board.getHit());
        setRegDate(board.getRegDate());
    }

    public int getArticleNo() {
        return articleNo;
    }

    public String getTitle() {
        return title;
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

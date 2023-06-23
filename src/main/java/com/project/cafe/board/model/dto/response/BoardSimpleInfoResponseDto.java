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

    public BoardSimpleInfoResponseDto convertToDto(BoardVO board) {
        BoardSimpleInfoResponseDto dto = new BoardSimpleInfoResponseDto();
        dto.setArticleNo(board.getArticleNo());
        dto.setTitle(board.getTitle());
        dto.setWriter(board.getWriter());
        dto.setHit(board.getHit());
        dto.setRegDate(board.getRegDate());
        return dto;
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
        if(title == null || title.trim().length() == 0) return;
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

    @Override
    public String toString() {
        return "BoardSimpleInfoResponseDto{" +
                "articleNo=" + articleNo +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", hit=" + hit +
                ", regDate=" + regDate +
                '}';
    }
}

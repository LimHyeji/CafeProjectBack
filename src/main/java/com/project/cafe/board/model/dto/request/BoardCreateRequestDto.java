package com.project.cafe.board.model.dto.request;


import com.project.cafe.exception.list.MaliciousAccessExcption;
import com.project.cafe.exception.list.NoCreateBoardException;

public class BoardCreateRequestDto {

    private String writer;
    private String title;
    private String content;

    public BoardCreateRequestDto() {
    }

    public BoardCreateRequestDto(String writer, String title, String content) {
        setWriter(writer);
        setTitle(title);
        setContent(content);
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setWriter(String writer) {
        if (writer == null || writer.trim().length() == 0) throw new MaliciousAccessExcption();
        this.writer = writer;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().length() == 0) throw new NoCreateBoardException("제목을 입력해주세요.");
        this.title = title;
    }

    public void setContent(String content) {
        if(content == null || content.trim().length() == 0) throw new NoCreateBoardException("내용을 입력해주세요.");
        this.content = content;
    }

    @Override
    public String toString() {
        return "BoardCreateRequestDto{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

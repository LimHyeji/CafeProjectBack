package com.project.cafe.board.model.dto.request;


import com.project.cafe.exception.list.MaliciousAccessExcption;
import com.project.cafe.exception.list.InvalidBoardException;
import com.project.cafe.util.api.RequestDto;

public class BoardCreateRequestDto implements RequestDto {
    private String writer;
    private String title;
    private String content;

    public BoardCreateRequestDto() {

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
    public void validation() {
        if (writer == null || writer.trim().length() == 0) throw new MaliciousAccessExcption();
        if (title == null || title.trim().length() == 0) throw new InvalidBoardException("제목을 입력해주세요.");
        if(content == null || content.trim().length() == 0) throw new InvalidBoardException("내용을 입력해주세요.");
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

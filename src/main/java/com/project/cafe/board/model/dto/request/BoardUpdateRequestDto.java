package com.project.cafe.board.model.dto.request;

import com.project.cafe.exception.list.MaliciousAccessExcption;
import com.project.cafe.exception.list.InvalidBoardException;
import com.project.cafe.util.api.RequestDto;

public class BoardUpdateRequestDto implements RequestDto {

    private int articleNo;
    private String writer;
    private String title;
    private String content;

    public BoardUpdateRequestDto() {
    }


    public int getArticleNo() {
        return articleNo;
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
    
    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    @Override
    public void validation() {
        if(writer == null || writer.trim().length() == 0) throw new MaliciousAccessExcption();
        if(content == null || content.trim().length() == 0) throw new InvalidBoardException("내용을 입력해주세요.");
        if(title == null || title.trim().length() == 0) throw new InvalidBoardException("제목을 입력해주세요.");
    }
}

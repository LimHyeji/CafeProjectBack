package com.project.cafe.exception.list;

public class NoDetailBoardException extends BusinessException{

    public NoDetailBoardException() {
        super("해당 게시글이 존재하지 않습니다.");
    }
}

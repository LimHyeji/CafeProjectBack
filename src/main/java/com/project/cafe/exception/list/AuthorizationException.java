package com.project.cafe.exception.list;

public class AuthorizationException extends BusinessException{

    public AuthorizationException() {
        super("해당 권한이 없습니다.");
    }
}

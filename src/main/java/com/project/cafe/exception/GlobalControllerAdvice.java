package com.project.cafe.exception;

import com.project.cafe.exception.list.ErrorResponse;
import com.project.cafe.exception.list.NoDetailBoardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    /*
        전역으로 에러를 관리하기 위한 ControllerAdvice.
        에러를 한 곳으로 모아 관리하기 위함
    */

    @ExceptionHandler(NoDetailBoardException.class)
    protected ResponseEntity<ErrorResponse> handleNoDetailBoardException(NoDetailBoardException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}

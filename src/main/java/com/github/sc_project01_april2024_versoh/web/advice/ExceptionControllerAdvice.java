package com.github.sc_project01_april2024_versoh.web.advice;

import com.github.sc_project01_april2024_versoh.service.exceptions.NotFoundException;
import com.github.sc_project01_april2024_versoh.service.exceptions.NotSameUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException nfe){
        log.error("Not Found Error message: "+ nfe.getMessage());
        return nfe.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotSameUserException.class)
    public String handleSoldOutException(NotSameUserException nsu){
        log.error("클라이언트 요청에 문제가 있습니다. 작성자가 아니므로 함부로 update, delete할 수 없습니다: "+ nsu.getMessage());
        return nsu.getMessage();
    }
}

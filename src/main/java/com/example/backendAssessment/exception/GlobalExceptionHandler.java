package com.example.backendAssessment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BackendAssesmentBadRequestException.class )
    public @ResponseBody ErrorResponse handleError(Exception e){
        log.error(e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = BackendAssesmentNotFoundException.class )
    public @ResponseBody ErrorResponse handleNotFoundError(Exception e){
        log.error(e.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class )
    public @ResponseBody ErrorResponse handleInternalError(Exception e){
        log.error(e.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    
}

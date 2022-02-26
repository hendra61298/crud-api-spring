package com.film.moviecrudapi.controller.interfaces;

import java.util.HashMap;

import com.film.moviecrudapi.common.baserespons.BaseResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ErrorHandlerInterface {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public default ResponseEntity<HashMap<String, Object>> handleException(MethodArgumentNotValidException e) {
        return new BaseResponse(false, null, e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                HttpStatus.BAD_GATEWAY)
                        .toResponse();
    }
}

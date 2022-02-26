package com.film.moviecrudapi.common.baserespons;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class BaseResponse {

    public BaseResponse(Boolean success, Object data, String message, HttpStatus statusCode) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
    }

    private Boolean success;
    private Object data;
    private String message;
    private HttpStatus statusCode;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> toResponse() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", this.success);
        map.put("data", this.data);
        map.put("message", this.message);
        return new ResponseEntity<HashMap<String, Object>>(map, statusCode);
    }
}

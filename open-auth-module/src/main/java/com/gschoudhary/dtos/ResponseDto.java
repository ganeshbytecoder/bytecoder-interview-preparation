package com.gschoudhary.dtos;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private int status;
    private String message;
    private T response;
}

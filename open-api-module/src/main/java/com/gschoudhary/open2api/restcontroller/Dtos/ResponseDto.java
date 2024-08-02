package com.gschoudhary.open2api.restcontroller.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {

    int status;
    String message;
    T response;
}

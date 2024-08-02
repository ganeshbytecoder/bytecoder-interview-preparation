package com.gschoudhary.Users;

import com.gschoudhary.open2api.restcontroller.JsonMapper;

import java.util.function.Function;

public interface OneApi<T, R> extends Function<T, R> {


    Class<T> getType();

    JsonMapper jsonMapper = new JsonMapper();

    default R execute(String object) throws Exception {
        return apply(jsonMapper.fromJson(object, getType()));
    }
}

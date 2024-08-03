package com.gschoudhary.open2api.restcontroller.router;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class Router {

    public static ConcurrentMap<String, Function<Object, Object>> post = new ConcurrentHashMap<>();

    public Router() {
        post = new ConcurrentHashMap<>();

    }


    public static void POST(String code, Function<Object, Object> function) {

        post.put(code, function);
    }
}

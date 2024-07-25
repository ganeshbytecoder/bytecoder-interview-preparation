package com.gschoudhary.open2api.utils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest
                .newBuilder()
                .header("Content-Type", "application/json")
                .build();
        System.out.println("interceptor ");
        return chain.proceed(requestWithUserAgent);
    }
}

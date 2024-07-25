package com.gschoudhary.open2api.utils.headers;

import okhttp3.Headers;

public class NoAuthHeaderServiceImpl implements AuthHeaderService {
    @Override
    public Headers getHeaders() {
        System.out.println("Making no Auth request");
        return new Headers.Builder().build();
    }
}

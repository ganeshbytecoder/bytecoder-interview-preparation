package com.gschoudhary.open2api.utils.headers;

import okhttp3.Headers;

@FunctionalInterface
public interface AuthHeaderService {

    Headers getHeaders() ;
}

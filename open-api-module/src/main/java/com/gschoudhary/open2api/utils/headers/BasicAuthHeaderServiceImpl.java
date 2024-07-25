package com.gschoudhary.open2api.utils.headers;

import com.gschoudhary.open2api.domain.AuthConfigEntity;
import okhttp3.Headers;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class BasicAuthHeaderServiceImpl implements AuthHeaderService {
    private AuthConfigEntity authConfig;

    public BasicAuthHeaderServiceImpl(AuthConfigEntity authConfig) {
        this.authConfig = authConfig;
    }

    @Override
    public Headers getHeaders() {
        System.out.println("Making basic Auth request");
        String auth = authConfig.getJsonObject();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII), false);
        String authHeader = "Basic " + new String(encodedAuth);
        return new Headers.Builder().add("Authorization", authHeader).build();
    }
}

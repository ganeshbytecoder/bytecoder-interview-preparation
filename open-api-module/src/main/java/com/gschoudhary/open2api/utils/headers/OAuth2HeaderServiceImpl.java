package com.gschoudhary.open2api.utils.headers;

import com.google.gson.Gson;
import com.gschoudhary.open2api.domain.AuthConfigEntity;
import com.gschoudhary.open2api.utils.RequestInterceptor;
import com.gschoudhary.open2api.utils.validator.JsonValidator;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OAuth2HeaderServiceImpl implements AuthHeaderService {

    private AuthConfigEntity authConfig;

    public OAuth2HeaderServiceImpl(AuthConfigEntity authConfig) {
        this.authConfig = authConfig;
    }

    @Override
    public Headers getHeaders() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse(authConfig.getMediaType()), authConfig.getJsonObject());
        Request request = new Request.Builder()
                .method(authConfig.getMethodType().getValue(), requestBody)
                .url(authConfig.getApiUrl())
                .build();
        Call call = client.newCall(request);

        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("access_token", "-----");
        try {
            String resp = response.body().string();
            System.out.println(resp);
            JsonValidator jsonValidator = new JsonValidator();
            System.out.println(jsonValidator.isValidJson(resp));
            map = gson.fromJson(resp, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        response.close();

        return new Headers.Builder().add("access_token", map.get("access_token")).build();
    }

}

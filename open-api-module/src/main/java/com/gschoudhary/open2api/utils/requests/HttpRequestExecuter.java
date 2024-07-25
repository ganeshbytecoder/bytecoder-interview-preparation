package com.gschoudhary.open2api.utils.requests;

import com.google.gson.Gson;
import com.gschoudhary.open2api.domain.ApiConfigEntity;
import com.gschoudhary.open2api.utils.RequestInterceptor;
import com.gschoudhary.open2api.utils.headers.AuthHeaderService;
import com.gschoudhary.open2api.utils.validator.JsonValidator;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestExecuter implements HttpRequestExecuterService {
    private AuthHeaderService authHeaderService;

    private ApiConfigEntity apiConfigEntity;

    public HttpRequestExecuter(AuthHeaderService authHeaderService, ApiConfigEntity apiConfigEntity) {
        this.authHeaderService = authHeaderService;
        this.apiConfigEntity = apiConfigEntity;
    }

    @Override
    public String execute() {
        System.out.println("executing thread " + Thread.currentThread());

        Headers headers = authHeaderService.getHeaders();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .build();
        RequestBody requestBody = RequestBody.create(MediaType.parse(apiConfigEntity.getMediaType()), apiConfigEntity.getJsonObject());
        Request request = new Request.Builder()
                .method(apiConfigEntity.getMethodType().getValue(), requestBody)
                .url(apiConfigEntity.getApiUrl())
                .headers(headers)
                .build();

        Call call = client.newCall(request);

        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", "-----");
        String resp = "";

        try {
            resp = response.body().string();
            System.out.println(resp);
            JsonValidator jsonValidator = new JsonValidator();
            System.out.println(jsonValidator.isValidJson(resp));
            map = gson.fromJson(resp, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        response.close();
        return resp;

    }


}

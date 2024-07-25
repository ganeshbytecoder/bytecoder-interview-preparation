package com.gschoudhary.open2api.service.syncApi;

import com.gschoudhary.open2api.domain.ApiConfigEntity;
import com.gschoudhary.open2api.domain.AuthConfigEntity;
import com.gschoudhary.open2api.repository.ApiConfigRepository;
import com.gschoudhary.open2api.repository.AuthConfigRepository;
import com.gschoudhary.open2api.utils.headers.AuthHeaderFactory;
import com.gschoudhary.open2api.utils.headers.AuthHeaderService;
import com.gschoudhary.open2api.utils.requests.HttpRequestExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

@Service
public class OneApiServiceImp {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    @Autowired
    private ApiConfigRepository apiConfigRepository;

    @Autowired
    private AuthConfigRepository authConfigRepository;

    @Autowired
    private AuthHeaderFactory authHeaderFactory;

    public String makeRequest(String code, String object, String requestType) throws ExecutionException, InterruptedException {
        System.out.println("making request call " + Thread.currentThread());


        ApiConfigEntity apiConfigEntity1 = apiConfigRepository.findByApiCode(code)
                .map(apiConfigEntity ->{
            apiConfigEntity.setJsonObject(object);
            return apiConfigEntity;
        } ).orElseThrow(() -> new IllegalArgumentException("Invalid api code"));


        Optional<AuthConfigEntity> authConfig = authConfigRepository.findByApiCode(apiConfigEntity1.getAuthApiCode());
        AuthHeaderService oAuth2HeaderService = authHeaderFactory.getAuthHeaderService(apiConfigEntity1.getAuthType(), authConfig);

        HttpRequestExecuter httpRequestExecuter = new HttpRequestExecuter(oAuth2HeaderService, apiConfigEntity1);

        if (requestType.equals("SYNC")) {
            Future<String> response = executor.submit(() -> httpRequestExecuter.execute());
            return response.get();

        } else if ((requestType.equals("ASYNC"))) {
            System.out.println("Making Async calls");
            Future<String> response = executor.submit(() -> httpRequestExecuter.execute());
            return "Request is successfully fired";
        }
//        executor.submit(new Thread());

        throw new RuntimeException("Request type is not defined");
    }


}

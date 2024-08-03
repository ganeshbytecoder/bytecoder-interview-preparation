package com.gschoudhary.open2api.restcontroller;


import com.gschoudhary.open2api.service.OneApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ServiceFactory {

    private ConcurrentMap<String, OneApi> serviceMap = new ConcurrentHashMap<>();


    @Autowired
    public ServiceFactory(ApplicationContext context) {
        Map<String, OneApi> services = context.getBeansOfType(OneApi.class);
        services.forEach((name, service) -> {
            serviceMap.put(name, service);
        });
    }

    public OneApi getService(String configCode) {
        OneApi service = serviceMap.get(configCode);
        if (service == null) {
            throw new IllegalArgumentException("Invalid config code: " + configCode);
        }
        return service;
    }
}
package com.gschoudhary.open2api.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

@Component
public class ServiceFactory {

    private ConcurrentMap<String, Function> serviceMap = new ConcurrentHashMap<>();


    @Autowired
    public ServiceFactory(ApplicationContext context) {
        Map<String, Function> services = context.getBeansOfType(Function.class);
        services.forEach((name, service) -> {
            serviceMap.put(name, service);
        });
    }

    public Function getService(String configCode) {
        Function service = serviceMap.get(configCode);
        if (service == null) {
            throw new IllegalArgumentException("Invalid config code: " + configCode);
        }
        return service;
    }
}
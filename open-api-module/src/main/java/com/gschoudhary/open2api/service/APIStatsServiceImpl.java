package com.gschoudhary.open2api.service;

import com.gschoudhary.open2api.repository.ApiStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class APIStatsServiceImpl implements BiConsumer<String,String> {

    @Autowired
    ApiStatsRepository apiStatsRepository;


    @Override
    public void accept(String code, String object) {

        System.out.println("Saving api stats" + object);

    }
}

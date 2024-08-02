package com.gschoudhary.open2api.service;

import com.gschoudhary.open2api.domain.ApiStatsEntity;
import com.gschoudhary.open2api.repository.ApiStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIStatsServiceImpl {

    @Autowired
    ApiStatsRepository apiStatsRepository;


    public ApiStatsEntity accept(String code, String object) {

        System.out.println("Saving api stats" + object);

        ApiStatsEntity apiStatsEntity = new ApiStatsEntity();

        apiStatsEntity.setApiCode(code);
        apiStatsEntity.setRequestBody(object);
        apiStatsEntity = apiStatsRepository.save(apiStatsEntity);
        System.out.println(apiStatsEntity);

        return apiStatsEntity;
    }

       public ApiStatsEntity update(long id, String object, int status) {

        System.out.println("updating api stats " + status);

        ApiStatsEntity apiStatsEntity = apiStatsRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("ApiStats is not found with id %d", id)));
        apiStatsEntity.setResponseBody(object);
        apiStatsEntity.setStatus(status);
        apiStatsEntity = apiStatsRepository.save(apiStatsEntity);
        System.out.println(apiStatsEntity);

        return apiStatsEntity;
    }
}

package com.bytecoder.DesignPatterns.LLD.structural.pipe_filters.example1;

import java.util.ArrayList;
import java.util.List;

import com.bytecoder.DesignPatterns.LLD.structural.pipe_filters.Filter;

public class EntityFilter implements Filter<List<UserDto>, List<UserEntity>> {
    @Override
    public List<UserEntity> execute(List<UserDto> userDtos) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserDto input :
                userDtos) {

            userEntities.add(new UserEntity(input.getName(), input.getAge()));
        }
        return userEntities;
    }
}

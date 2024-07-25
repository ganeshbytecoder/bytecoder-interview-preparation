package com.bytecoder.DesignPatterns.structural.pipe_filters.example1;

import java.util.ArrayList;
import java.util.List;

import com.bytecoder.DesignPatterns.structural.pipe_filters.Filter;

public class DtoFilter implements Filter<List<String>, List<UserDto>> {
    @Override
    public List<UserDto> execute(List<String> lines) {
       List<UserDto> userDtos = new ArrayList<>();
        for (String input: lines) {
            String[] str =  input.split(",");
            userDtos.add(new UserDto(str[0].trim(), Integer.parseInt(str[1].trim())));
        }
        return userDtos;
    }
}

package com.gschoudhary.Users;


import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service("A")
public class UsersServiceImpl implements Function<UserDto, String> {


    private UsersServiceImpl() {
    }


    @Override
    public String apply(UserDto userDto) {
        System.out.println(userDto.toString());
        return "Successfully Saved";
    }
}

package com.gschoudhary.Users;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;



@NoArgsConstructor
@Service("A")
class Get implements OneApi<UserDto, UserDto> {


    @Override
    public UserDto apply(UserDto userDto) {
        System.out.println(userDto.toString());
        return userDto;
    }

    @Override
    public Class<UserDto> getType() {
        return UserDto.class;
    }
}


@Service("B")
class Post implements OneApi<UserDto, String> {

    @Override
    public String apply(UserDto userDto) {
        System.out.println(userDto.toString());
        return "Successfully Saved B";
    }

    @Override
    public Class<UserDto> getType() {
        return UserDto.class;
    }
}





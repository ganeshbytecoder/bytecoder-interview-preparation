package com.gschoudhary.Bytecoder.users;

import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {


    @Override
    public UserDto create(UserDto userDto) {
        System.out.println(userDto);

        return userDto;
    }

    @Override
    public UserDto getAll(UserDto userDto) {
        System.out.println(userDto);

        return userDto;
    }


    @Override
    public UserDto getById(UserDto userDto) {
        System.out.println(userDto);

        return userDto;
    }


    @Override
    public UserDto update(UserDto userDto) {
        System.out.println(userDto);

        return userDto;
    }


    @Override
    public UserDto delete(UserDto userDto) {
        System.out.println(userDto);

        return userDto;
    }
}

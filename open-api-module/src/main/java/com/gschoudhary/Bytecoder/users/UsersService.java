package com.gschoudhary.Bytecoder.users;


public interface UsersService {


    UserDto create(UserDto UserDto);

    UserDto getAll(UserDto UserDto);

    UserDto getById(UserDto UserDto);

    UserDto update(UserDto UserDto);

    UserDto delete(UserDto UserDto);
}

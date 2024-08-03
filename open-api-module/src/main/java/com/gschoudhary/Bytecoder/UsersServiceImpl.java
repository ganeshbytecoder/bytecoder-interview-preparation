package com.gschoudhary.Bytecoder;


import com.gschoudhary.open2api.service.OneApi;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl {

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

}

@NoArgsConstructor
@Service("A")
class Get extends UsersServiceImpl implements OneApi<UserDto, UserDto> {


    @Override
    public UserDto apply(UserDto userDto) {
        System.out.println(userDto.toString());
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
//        userEntity = userRepository.save(userEntity);
        System.out.println(userEntity);
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





package com.gschoudhary.Bytecoder.users;


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
class Get  implements OneApi<UserDto, UserDto> {


    @Override
    public UserDto apply(UserDto userDto) {
        System.out.println(userDto.toString());
//        userEntity = userRepository.save(userEntity);
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





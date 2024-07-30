package com.gschoudhary.services;


import com.gschoudhary.dtos.UserRequest;
import com.gschoudhary.dtos.UserResponse;
import com.gschoudhary.models.UserInfo;
import com.gschoudhary.models.UserRole;
import com.gschoudhary.repositories.RoleRepository;
import com.gschoudhary.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository  roleRepository;

    ModelMapper modelMapper = new ModelMapper();



    @Override
    public UserResponse saveUser(UserRequest userRequest) {

        UserInfo savedUser = null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userRequest.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        for(UserRole userRole: userRequest.getRoles()){
            Optional<UserRole> userRole1 = roleRepository.findByName(userRole.getName());
            if(!userRole1.isPresent()){
                roleRepository.save(userRole);
            }else {
                System.out.println("present");
            }
        }

        UserInfo user = modelMapper.map(userRequest, UserInfo.class);
        System.out.println(user.toString());
        user.setPassword(encodedPassword);
        if(userRequest.getId() != null){
            UserInfo oldUser = userRepository.findFirstById(userRequest.getId());
            if(oldUser != null){
                oldUser.setId(user.getId());
                oldUser.setPassword(user.getPassword());
                oldUser.setUsername(user.getUsername());
                oldUser.setRoles(user.getRoles());

                savedUser = userRepository.save(oldUser);
            } else {
                throw new RuntimeException("Can't find record with identifier: " + userRequest.getId());
            }
        } else {
            savedUser = userRepository.save(user);
        }
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        return userResponse;
    }

    @Override
    public UserResponse getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String usernameFromAccessToken = userDetail.getUsername();
        UserInfo user = userRepository.findByUsername(usernameFromAccessToken);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<UserInfo> users = (List<UserInfo>) userRepository.findAll();
        Type setOfDTOsType = new TypeToken<List<UserResponse>>(){}.getType();
        List<UserResponse> userResponses = modelMapper.map(users, setOfDTOsType);
        return userResponses;
    }


}

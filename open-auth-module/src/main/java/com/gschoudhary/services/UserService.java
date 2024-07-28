package com.gschoudhary.services;


import com.gschoudhary.dtos.UserRequest;
import com.gschoudhary.dtos.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();


}

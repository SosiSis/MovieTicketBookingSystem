package com.itsc.movie.convertor;

import com.itsc.movie.entities.User;
import com.itsc.movie.request.UserRequest;
import com.itsc.movie.response.UserResponse;

public class UserConvertor {

    public static User userDtoToUser(UserRequest userRequest, String password) {
        User user = User.builder() // Use the builder pattern for consistency and cleaner code
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .address(userRequest.getAddress())
                .mobileNo(userRequest.getMobileNo())
                .emailId(userRequest.getEmailId())
                .gender(userRequest.getGender())
                .roles(userRequest.getRoles())  // Set the roles
                .password(password)
                .build();
        return user;
    }

    public static UserResponse userToUserDto(User user) {
        return UserResponse.builder() // Use the builder for UserResponse as well
                .name(user.getName())
                .age(user.getAge())
                .address(user.getAddress())
                .gender(user.getGender())
                .build();
    }

    
}
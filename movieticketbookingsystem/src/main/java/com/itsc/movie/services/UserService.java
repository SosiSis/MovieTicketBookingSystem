package com.itsc.movie.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itsc.movie.convertor.UserConvertor;
import com.itsc.movie.entities.User;
import com.itsc.movie.exceptions.UserExist;
import com.itsc.movie.repositories.UserRepository;
import com.itsc.movie.request.UserRequest;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String addUser(UserRequest userRequest) {
		Optional<User> users = userRepository.findByEmailId(userRequest.getEmailId());
		
		if (users.isPresent()) {
			throw new UserExist();
		}

		User user = UserConvertor.userDtoToUser(userRequest, passwordEncoder.encode(userRequest.getPassword())); // Use userRequest's password
		

		


		userRepository.save(user);
		return "User Saved Successfully";
	}

	// Method to get userId by username
    public Integer getUserIdByUsername(String username) {
        return userRepository.findByEmailId(username)
                             .map(user -> user.getId())
                             .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
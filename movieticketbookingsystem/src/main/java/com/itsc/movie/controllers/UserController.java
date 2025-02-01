package com.itsc.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsc.movie.config.JWTService;
import com.itsc.movie.request.UserRequest;
import com.itsc.movie.response.TokenResponse;
import com.itsc.movie.services.UserService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	@PostMapping("/addNew")
	@PreAuthorize("permitAll()")
	public ResponseEntity<String> addNewUser(@RequestBody UserRequest userEntryDto) {
		try {
			String result = userService.addUser(userEntryDto);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/getToken")
    public ResponseEntity<TokenResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            // Generate token
            String token = jwtService.generateToken(authRequest.getUsername());

            // Retrieve the user's role (this could be a custom method to fetch user role)
            String role = authentication.getAuthorities().stream()
                                        .map(authority -> authority.getAuthority())
                                        .findFirst() // Assumes only one role, or you can adapt for multiple roles
                                        .orElse("ROLE_USER"); // Default if no role found

            // Retrieve userId from the authenticated user (assuming UserService has a method to get user details)
            Integer userId = userService.getUserIdByUsername(authRequest.getUsername());

            // Create response object
            TokenResponse tokenResponse = new TokenResponse(token, role, userId);

            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        }

        throw new UsernameNotFoundException("Invalid user details.");
    }
}

package com.globallogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dto.JwtResponse;
import com.globallogic.dto.LoginDto;
import com.globallogic.dto.SignUpDto;
import com.globallogic.entity.User;
import com.globallogic.repository.UserRepository;
import com.globallogic.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	/**
	 * Requirements for login
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Requirements for signup
	 */
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * JWT
	 */
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/**
		 * get token from token provider class
		 */
		String token=jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtResponse(token));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpDto signUpDto) {

		/**
		 * Check for username already exists or not
		 */
		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("username is already taken", HttpStatus.BAD_REQUEST);
		}

		/**
		 * Check for email already exists or not
		 */
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<String>("email is already taken", HttpStatus.BAD_REQUEST);
		}

		/**
		 * create User object and set values using signUpDto
		 */
		User user = new User();
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		// encode password
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		user.setRole(signUpDto.getRole());

		userRepository.save(user);
		return new ResponseEntity<String>("Sign up is successful", HttpStatus.OK);

	}

}

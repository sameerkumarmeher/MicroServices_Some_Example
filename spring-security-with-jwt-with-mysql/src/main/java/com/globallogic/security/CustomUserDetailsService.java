package com.globallogic.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.globallogic.entity.User;
import com.globallogic.exception.UserNameNotFoundException;
import com.globallogic.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UserNameNotFoundException("User not found with username or email " + usernameOrEmail));

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
	}
	

}

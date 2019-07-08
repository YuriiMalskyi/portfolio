package com.malskyi.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malskyi.project.entity.UserEntity;
import com.malskyi.project.repository.UserRepository;

@Service
@Transactional
public class UserEntityDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException("User with username '" + username + "' not found!");
		}
		
		return User
				.builder()
					.username(userEntity.getUsername())
					.password(userEntity.getPassword())
					.authorities(userEntity.getRole())
				.build();
	}

	
	
}

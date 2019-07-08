package com.malskyi.project.service.impl;

import static com.malskyi.project.constants.ErrorMessages.RECORD_ALREADY_EXISTS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malskyi.project.config.jwt.JWTTokenProvider;
import com.malskyi.project.domain.UserEntityDTO;
import com.malskyi.project.entity.UserEntity;
import com.malskyi.project.exceptions.UserServiceException;
import com.malskyi.project.repository.UserRepository;
import com.malskyi.project.service.UserEntityService;
import com.malskyi.project.service.utils.ObjectMapperUtils;
import com.malskyi.project.service.utils.StringUtils;

@Service
@Transactional
public class UserEntityServiceImpl implements UserEntityService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	private StringUtils stringUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void save(UserEntityDTO dto) {
		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new UserServiceException(RECORD_ALREADY_EXISTS);
		}else {
			dto.setRole(dto.getRole());
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));
			userRepository.save(objectMapperUtils.map(dto, UserEntity.class));
		}		
	}

	@Override
	public UserEntityDTO findById(int id) {
		return objectMapperUtils.map(userRepository.findById(id), UserEntityDTO.class);
	}

	@Override
	public List<UserEntityDTO> findAll() {
		return objectMapperUtils.mapAll(userRepository.findAll(), UserEntityDTO.class);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public UserEntityDTO findByUsername(String username) {
		return objectMapperUtils.map(userRepository.findByUsername(username), UserEntityDTO.class);
	}

	@Override
	public String signin(String username, String password) {
		System.out.println(">>> " + username);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		System.out.println(">>> " + password);
		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole());
	}

	@Override
	public void deleteUser(String username) {
		userRepository.deleteById(userRepository.findByUsername(username).getId());
//		userRepository.deleteByUsername(username);
	}

	@Override
	public void updateUser(UserEntityDTO userEntityDTO) {
		if(userEntityDTO.getPassword() != "") {
		userRepository.updateUser(userEntityDTO.getId(), 
								  userEntityDTO.getUsername(), 
								  userEntityDTO.getPassword(), 
								  userEntityDTO.getFirstName(), 
								  userEntityDTO.getLastName(), 
								  userEntityDTO.getRole());
		}else {
		userRepository.updateUserNoPass(userEntityDTO.getId(), 
				  userEntityDTO.getUsername(), 
				  userEntityDTO.getFirstName(), 
				  userEntityDTO.getLastName(), 
				  userEntityDTO.getRole());
		}
	}

}

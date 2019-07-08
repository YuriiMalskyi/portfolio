package com.malskyi.project.service;

import java.util.List;

import com.malskyi.project.domain.UserEntityDTO;


public interface UserEntityService {
	
	void save(UserEntityDTO dto);
	
	UserEntityDTO findById(int id);
	
	List<UserEntityDTO> findAll();
	
	boolean existsByUsername(String username);
	
	UserEntityDTO findByUsername(String username);
	
	void deleteUser(String username);
	
	void updateUser(UserEntityDTO userEntityDTO);
	
	String signin(String username, String password);
}

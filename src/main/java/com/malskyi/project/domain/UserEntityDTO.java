package com.malskyi.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malskyi.project.entity.enums.Roles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEntityDTO {
	
//	@JsonIgnore
	private int id;

//	private String userId;
	
	private String username;

	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Roles role;
	
}

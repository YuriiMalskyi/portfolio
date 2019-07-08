package com.malskyi.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

import com.malskyi.project.entity.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
//@Table(indexes = @Index(columnList = "userId"))
@Table(name = "users")
public class UserEntity extends BaseEntity{

//		@Column(unique = true)
//		private String userId;
	
		@Column(nullable = false, unique = true)
		private String username;
		
		@Column(nullable = false)
		private String password;
		
		private String firstName;
	
		private String lastName;
	
		@Enumerated(EnumType.STRING)
		private Roles role;

}

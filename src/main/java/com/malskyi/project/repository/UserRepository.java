package com.malskyi.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.malskyi.project.entity.UserEntity;
import com.malskyi.project.entity.enums.Roles;

//@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer>{

	UserEntity findByUsername(String username);
	
	UserEntity findById(int id);

	void deleteById(int id);
	
	boolean existsByUsername(String username);
	
	@Modifying//(clearAutomatically = true)
	@Query("UPDATE UserEntity ue SET ue.username = :username, "
			+ "ue.password = :password, ue.firstName = :firstName, ue.lastName = :lastName, ue.role = :role"
			+ " where ue.id = :id")
	void updateUser(@Param("id") int id, 
					@Param("username") String username, 
					@Param("password") String password, 
					@Param("firstName") String firstName, 
					@Param("lastName") String lastName, 
					@Param("role") Roles role);
	
	@Modifying//(clearAutomatically = true)
	@Query("UPDATE UserEntity ue SET ue.username = :username, "
			+ "ue.firstName = :firstName, ue.lastName = :lastName, ue.role = :role"
			+ " where ue.id = :id")
	void updateUserNoPass(@Param("id") int id, 
					@Param("username") String username,
					@Param("firstName") String firstName, 
					@Param("lastName") String lastName, 
					@Param("role") Roles role);
}
//
//@Column(nullable = false, unique = true)
//private String username;
//
//@Column(nullable = false)
//private String password;
//
//private String firstName;
//
//private String lastName;
//
//@Enumerated(EnumType.STRING)
//private Roles role;
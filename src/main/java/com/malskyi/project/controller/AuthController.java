package com.malskyi.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malskyi.project.domain.UserEntityDTO;
import com.malskyi.project.domain.request.SigninRequest;
import com.malskyi.project.domain.response.SigninResponse;
import com.malskyi.project.service.UserEntityService;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private UserEntityService userService;
		
	@PostMapping("signup")
	public ResponseEntity<Void> registerUser(@RequestBody UserEntityDTO dto){
		userService.save(dto);		
		return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	@PostMapping("signin")
	public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
		String token = userService.signin(request.getUsername(), request.getPassword());
		String role = "";
		System.out.println(token + "\n" + request.getUsername() + "\n" + request.getPassword());
		
		if(token != null) {
			role = userService.findByUsername(request.getUsername()).getRole().toString();
			System.out.println("ROLE: " + role);
		}
		
		return new ResponseEntity<SigninResponse>(new SigninResponse(token, role), HttpStatus.OK);
	}
	
}

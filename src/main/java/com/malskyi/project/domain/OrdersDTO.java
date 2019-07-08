package com.malskyi.project.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malskyi.project.entity.Commodity;
import com.malskyi.project.entity.Items;
import com.malskyi.project.entity.enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersDTO {

//	@JsonIgnore
	private int id;

//	private String orderId;
	
	private LocalDate date;
	
	private Status status;
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	private String phoneNumber;
	
	private List<ItemsDTO> items;
	
}

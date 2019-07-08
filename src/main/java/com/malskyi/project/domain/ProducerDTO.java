package com.malskyi.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerDTO {

//	@JsonIgnore
	private int id;

//	private String producerId;
	
	private String name;
	
}

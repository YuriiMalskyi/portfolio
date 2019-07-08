package com.malskyi.project.domain;

import com.malskyi.project.entity.Commodity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemsDTO {
	
	private int count;	

	private Commodity commodity;
	
}

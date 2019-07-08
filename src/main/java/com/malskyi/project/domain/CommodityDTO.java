package com.malskyi.project.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malskyi.project.entity.Category;
import com.malskyi.project.entity.Producer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommodityDTO implements Comparable<CommodityDTO> {

//	@JsonIgnore
	private int id;
	
//	private String commodityStringId;
	
	private String name;
	
	private String description;
	
	private String imageURL;
	
	private int count;

	private BigDecimal price;
	
	private Category category;
	
	private Producer producer;

	@Override
	public int compareTo(CommodityDTO o) {
		return o.price.compareTo(this.price);
	}
	
	public String toStringLog() {
		return ("\n[ Id : " + id + " | Name : " + name + " | " + "Price : " + price + " | Category : " + category.getName() + " | Producer : " + producer.getName() + " ]\n");
	}
}

package com.malskyi.project.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "commodities")
//@Table(indexes = @Index(columnList = "commodityId"))
public class Commodity extends BaseEntity{

//	@Column(unique = true, nullable = false)
//	private String commodityId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(length = 10000)
	@Size(max = 10000)
	private String description;
	private String imageURL;
	private int count;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
	private BigDecimal price;
	
	@OneToOne
	@JoinColumn(name = "categories_id")
	private Category category;
	
	@OneToOne
	@JoinColumn(name = "producers_id")
	private Producer producer;
	
}

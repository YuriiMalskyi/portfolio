package com.malskyi.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
//@Table(indexes = @Index(columnList = "categoryId"))
@Table(name="categories")
public class Category extends BaseEntity{

	@Column(nullable = false, unique = true)
	private String name;
		
//	@Column(nullable = false, unique = true)
//	private String categoryId;
	
}

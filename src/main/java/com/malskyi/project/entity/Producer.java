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
//@Table(indexes = @Index(columnList = "producerId"))
@Table(name="producers")
public class Producer extends BaseEntity{

	@Column(nullable = false, unique = true)
	private String name;

//	@Column(unique = true)
//	private String producerId;
}

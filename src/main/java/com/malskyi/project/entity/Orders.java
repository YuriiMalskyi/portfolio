package com.malskyi.project.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.malskyi.project.entity.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
//@Table(indexes = @Index(columnList = "ordersId"))
@Table(name="orders")
public class Orders extends BaseEntity{

//	@Column(nullable = false, unique = true)
//	private String orderId;
	
	@Column(nullable = false)
	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false, length = 10)
	private String phoneNumber;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orders")
	@OneToMany(mappedBy = "orders")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<Items> items;
		
}

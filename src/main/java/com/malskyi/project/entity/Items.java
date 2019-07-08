package com.malskyi.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "items")
public class Items extends BaseEntity{

	int count;
	
	@OneToOne
	@JoinColumn(name = "commodities_id")
	private Commodity commodity;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "orders_id", nullable = false, referencedColumnName="id")
	private Orders orders;
		
	public String toStringLog() {
		return ("\n[ Count : " + count + 
				" | Commodity.name : " + commodity.getName() + 
				" | Commodity.Category.name : " + commodity.getCategory().getName() + 
				" | Commodity.Producer.name : " + commodity.getProducer().getName() + 
				" ]\n");
		}
}


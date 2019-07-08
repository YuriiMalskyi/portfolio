package com.malskyi.project.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.malskyi.project.entity.Producer;
import com.malskyi.project.entity.enums.Roles;
	
//@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer>{

//	Producer findByProducerId(String producerId);	
	
	Producer findByName(String name);
	
	void deleteById(int id);
	
	//Утворення списку виробників, товари яких є в наявності у магазині
	@Query("select p from Commodity c "
			+ "join Producer p on c.producer.id = p.id "
			+ "join Category ct on ct.id = c.category.id "
			+ "where ct.name = :categoryName and c.count > 0")
	Set<Producer> findAllByCategoryName(@Param("categoryName") String categoryName);

	@Query("select count(c) from Commodity c "
			+ "join Producer p on c.producer.id = p.id "
			+ "join Category ct on ct.id = c.category.id "
			+ "where p.name = :producerName and ct.name = :categoryName and c.count > 0")
	int findCommodityCountOfProducerByProducerAndCategoryNames(@Param("producerName") String producerName, @Param("categoryName") String categoryName);

	
	@Modifying//(clearAutomatically = true)
	@Query("UPDATE Producer p SET p.name = :name"
			+ " where p.id = :id")
	void updateProducer(@Param("id") int id, 
					@Param("name") String name);
}



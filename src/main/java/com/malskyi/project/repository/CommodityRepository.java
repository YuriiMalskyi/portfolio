package com.malskyi.project.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.malskyi.project.entity.Category;
import com.malskyi.project.entity.Commodity;
import com.malskyi.project.entity.Producer;

//@Repository
public interface CommodityRepository  extends JpaRepository<Commodity, Integer>{
	
	void deleteById(int id);
	
	Commodity findById(int id);
	
	List<Commodity> findAllByCategoryId(int categoryId);

	@Query("Select c FROM Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+ "where ct.name = ?1 "
			+ "order by c.name")
	List<Commodity> findAllByCategoryName(String name); //findAllByCategoryName(String name);

	@Query("Select c FROM Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+ "Join Producer p on c.producer.id = p.id "
			+ "where ct.name = ?1 and p.name = ?2")
	List<Commodity> findAllByCategoryAndProducerNames(String categoryName, String producerName); //findAllByCategoryName(String name);
	
	@Query("Select c FROM Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+ "where ct.name = ?1 and c.price between ?2 and ?3")
	List<Commodity> findAllByCategoryNameInPriceRange(String name, BigDecimal min, BigDecimal max);

	@Query("Select c FROM Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+ "where ct.name = ?1 and c.price between ?2 and ?3")
	List<Commodity> findAllByCategoryNameInPriceRangeWithOrder(String name, BigDecimal min, BigDecimal max);
	
	@Query("Select c FROM Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+ "where ct.name = ?1 and c.price between ?2 and ?3")
	List<Commodity> findAllByCategoryNameInPriceRangeWithOrder(String name, BigDecimal min, BigDecimal max, Sort sort);
	
	@Query("Select c FROM Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+ "Join Producer p on c.producer.id = p.id "
			+ "where ct.name = ?1 and p.name = ?2 and c.price between ?3 and ?4")
	List<Commodity> getAllByCategoryAndProducerNamesInPriceRange(String categoryName, String producerName, BigDecimal min, BigDecimal max);
//	List<Commodity> getAllByCategoryAndProducerNamesInPriceRangeWithOrder(String categoryName, String producerName, BigDecimal min, BigDecimal max, Sort sort);
	
	@Query("Select c FROM Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+ "where ct.name = ?1 ")
	List<Commodity> findAllByCategoryNameWithOrder(String categoryName, Sort sort);
//	
//	@Query("Select c FROM Commodity c "
//			+ "Join Category ct on c.category.id = ct.id "
//			+ "where ct.name = :name "
//			+ "order by c.name")
//	List<Commodity> findAllByPrice(@Param("name") String name);
	
	List<Commodity> findAllByProducerId(int producerId);
	
	@Query("Select (max(c.id)+1) FROM Commodity c")
	int getNextCommodityId();
	
	@Query("SELECT c.id FROM Commodity c where c.id=(SELECT max(c.id) FROM Commodity c)")
	int getLastCreatedCommodityId();
	
	@Modifying//(clearAutomatically = true)
	@Query("UPDATE Commodity cmm SET "
			+ "cmm.name = :name, "
			+ "cmm.description = :description, "
			+ "cmm.imageURL = :imageURL, "
			+ "cmm.count = :count, "
			+ "cmm.price = :price, "
			+ "cmm.category = :category,"
			+ "cmm.producer = :producer"
			+ " where cmm.id = :id")
	void updateCommodity(@Param("id") int id, 
					@Param("name") String name, 
					@Param("description") String description, 
					@Param("imageURL") String imageURL, 
					@Param("count") int count, 
					@Param("price") BigDecimal price,//,
					@Param("category") Category category,
					@Param("producer") Producer producer
					);
	
	@Modifying//(clearAutomatically = true)
	@Query("UPDATE Commodity c SET "
			+ "c.name = :name "
			+ "where c.id = :id")
	void updateCommodityTest(@Param("id") int id, 
					@Param("name") String name
					);
	
	@Modifying//(clearAutomatically = true)
	@Query("UPDATE Commodity cmm SET "
			+ "cmm.name = :name, "
			+ "cmm.description = :description, "
			+ "cmm.count = :count, "
			+ "cmm.price = :price, "
			+ "cmm.category = :category,"
			+ "cmm.producer = :producer"
			+ " where cmm.id = :id")
	void updateCommodityWithoutImage(@Param("id") int id, 
					@Param("name") String name, 
					@Param("description") String description, 
					@Param("count") int count, 
					@Param("price") BigDecimal price,//,
					@Param("category") Category category,
					@Param("producer") Producer producer
					);
	
	@Query("SELECT min(c.price) from Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+"where ct.name = :categoryName")
	int getCheapestProductPrice(@Param("categoryName") String categoryName);
	

	@Query("SELECT max(c.price) from Commodity c "
			+ "Join Category ct on c.category.id = ct.id "
			+"where ct.name = :categoryName")
	int getMostExpensiveProductPrice(@Param("categoryName") String categoryName);
}
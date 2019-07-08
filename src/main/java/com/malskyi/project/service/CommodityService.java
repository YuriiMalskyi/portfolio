package com.malskyi.project.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.malskyi.project.domain.CommodityDTO;

public interface CommodityService {

	void createCommodity(CommodityDTO commodityDTO);
	
//	String getLastCreatedCommodityId();
	
	int getNextCommodityId();
	
	void updateCommodity(CommodityDTO commodityDTO, boolean image_upd);
		
	List<CommodityDTO> getAllByCategoryId(int categoryId);
	
	List<CommodityDTO> getAllByCategoryName(String name);

	List<CommodityDTO> getAllByCategoryNameInPriceRange(String name, BigDecimal min, BigDecimal max);
	
	List<CommodityDTO> getAllByCategoryNameInPriceRangeWithOrder(String categoryName, BigDecimal minPrice, BigDecimal maxPrice, String order_type);
	
	List<CommodityDTO> getAllByCategoryNameWithOrder(String categoryName, String order_type);
	
	List<CommodityDTO> getAllByProducerId(int producerId);
	
	void uploadImage(MultipartFile file, int commodityId);
		
	List<CommodityDTO> getAll();
	
	CommodityDTO getById(int id);
	
//	List<CommodityDTO> getAll(Pageable pageable);
	
	void deleteCommodity(int id);
	
	void deleteImage(int id);

	String updateImage(MultipartFile file, int commodityId);
	
	int getCheapestProductPrice(String categoryName);

	int getMostExpensiveProductPrice(String categoryName);
			
	List<CommodityDTO> getAllByCategoryAndProducerNamesInPriceRangeWithOrder(String categoryName, BigDecimal minPrice, BigDecimal maxPrice, String order_type, String... producerNames);
}

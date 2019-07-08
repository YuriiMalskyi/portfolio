package com.malskyi.project.service.impl;

import static com.malskyi.project.constants.ErrorMessages.NO_RECORD_FOUND;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.malskyi.project.domain.CommodityDTO;
import com.malskyi.project.entity.Commodity;
import com.malskyi.project.exceptions.CommodityNotFoundException;
import com.malskyi.project.repository.CommodityRepository;
import com.malskyi.project.service.CommodityService;
import com.malskyi.project.service.cloudinary.CloudinaryService;
import com.malskyi.project.service.utils.ObjectMapperUtils;
import com.malskyi.project.service.utils.StringUtils;

@Service
@Transactional
public class CommodityServiceImpl implements CommodityService{

	@Autowired
	private CommodityRepository commodityRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void createCommodity(CommodityDTO commodityDTO) {
		commodityRepository.save(objectMapperUtils.map(commodityDTO, Commodity.class));
	}

	@Override
	public void updateCommodity(CommodityDTO commodityDTO, boolean image_upd) {
		if(image_upd == true){
			commodityRepository.updateCommodity(
					commodityDTO.getId(), 
					commodityDTO.getName(), 
					commodityDTO.getDescription(),
					commodityDTO.getImageURL(),
					commodityDTO.getCount(),
					commodityDTO.getPrice(),//,
					commodityDTO.getCategory(),
					commodityDTO.getProducer()
					);
		}else {
			 commodityRepository.updateCommodityWithoutImage(
					commodityDTO.getId(), 
					commodityDTO.getName(), 
					commodityDTO.getDescription(),
					commodityDTO.getCount(),
					commodityDTO.getPrice(),//,
					commodityDTO.getCategory(),
					commodityDTO.getProducer()
					);
		}				
	}

	@Override
	public List<CommodityDTO> getAllByCategoryId(int categoryId) {
		return objectMapperUtils.mapAll(commodityRepository.findAllByCategoryId(categoryId), CommodityDTO.class);
	}

	@Override
	public List<CommodityDTO> getAllByCategoryName(String name) {
		return objectMapperUtils.mapAll(commodityRepository.findAllByCategoryName(name), CommodityDTO.class);
	}
	
	@Override
	public List<CommodityDTO> getAllByCategoryNameInPriceRange(String name, BigDecimal  min, BigDecimal max){
		return objectMapperUtils.mapAll( commodityRepository.findAllByCategoryNameInPriceRange(name, min, max), CommodityDTO.class);
	}
	
	@Override
	public List<CommodityDTO> getAllByCategoryNameInPriceRangeWithOrder(String categoryName, BigDecimal minPrice, BigDecimal maxPrice, String order_type){
		
		List<CommodityDTO> list;
		Sort sort;
		switch(order_type) {
		
		case "name_asc":
			sort = new Sort(Direction.ASC, "name");
			break;
		
		case "name_desc":
			sort = new Sort(Direction.DESC, "name");
			break;
		
		case "price_asc":
			sort = new Sort(Direction.ASC, "price");
			break;
		
		case "price_desc":
			sort = new Sort(Direction.DESC, "price");
			break;
			
		default:
			System.out.println("\n\n\n\n ERROR \n\n\n\n");
			return null;
		}

		System.out.println("\n\n\n\n\n\n\n" + sort.toString() + "\n\n\n\n\n\n");
		list = objectMapperUtils.mapAll(commodityRepository.findAllByCategoryNameInPriceRangeWithOrder(categoryName, minPrice, maxPrice, sort), CommodityDTO.class);
		return list;
				
	}
	
	@Override
	public List<CommodityDTO> getAllByCategoryNameWithOrder(String categoryName, String order_type){
		List<CommodityDTO> list;
		Sort sort;
		switch(order_type) {
		
		case "name_asc":
			sort = new Sort(Direction.ASC, "name");
			break;
		
		case "name_desc":
			sort = new Sort(Direction.DESC, "name");
			break;
		
		case "price_asc":
			sort = new Sort(Direction.ASC, "price");
			break;
		
		case "price_desc":
			sort = new Sort(Direction.DESC, "price");
			break;
			
		default:
			System.out.println("\n\n\n\n ERROR \n\n\n\n");
			return null;
		}

		System.out.println("\n\n\n\n\n\n\n" + sort.toString() + "\n\n\n\n\n\n");
		list = objectMapperUtils.mapAll(commodityRepository.findAllByCategoryNameWithOrder(categoryName, sort), CommodityDTO.class);
		return list;
	}
		
	@Override
	public List<CommodityDTO> getAllByProducerId(int producerId) {
		return objectMapperUtils.mapAll(commodityRepository.findAllByProducerId(producerId), CommodityDTO.class);
	}
	
	@Override
	public void uploadImage(MultipartFile file, int commodityId) {
//		String imageURL = cloudinaryService.uploadFile(file, "commodity_name: " + commodityRepository.findByCommodityStringId(commodityStringId).getName());
		String imageURL = cloudinaryService.uploadFile(file, commodityId);
		
//		Commodity commodity = commodityRepository.getOne(commodityId);
		Commodity commodity = commodityRepository.findById(commodityId);
		if(commodity == null) {
			throw new CommodityNotFoundException(NO_RECORD_FOUND);
		}
		
		commodity.setImageURL(imageURL);
		commodityRepository.save(commodity);		
	}
	
	@Override
	public String updateImage(MultipartFile file, int commodityId) {
		String imageURL = cloudinaryService.uploadFile(file, commodityId);
		
		Commodity commodity = commodityRepository.findById(commodityId);
		if(commodity == null) {
			throw new CommodityNotFoundException(NO_RECORD_FOUND);
		}
		
		commodity.setImageURL(imageURL);
		commodityRepository.save(commodity);
		System.out.println("\n\n\n\n\n" + commodity.getImageURL() + "\n\n\n\n\n");
		return commodity.getImageURL();
	}
	
	@Override
	public List<CommodityDTO> getAll() {
		return objectMapperUtils.mapAll(commodityRepository.findAll(), CommodityDTO.class);
	}

//	@Override
//	public List<CommodityDTO> getAll(Pageable pageable) {
//		
//		Page<Commodity> page = commodityRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
//		List<Commodity> commodities = page.getContent();
//		
//		return objectMapperUtils.mapAll(commodities, CommodityDTO.class);
//	}


	@Override
	public CommodityDTO getById(int id) {
		return objectMapperUtils.map(commodityRepository.findById(id), CommodityDTO.class);
	}

	@Override
	public void deleteCommodity(int id) {
		commodityRepository.deleteById(id);
		cloudinaryService.deleteFile("image-" + id);
	}

	@Override
	public void deleteImage(int id) {
		cloudinaryService.deleteFile("image-" + id);
	}

	@Override
	public int getNextCommodityId() {
		return commodityRepository.getNextCommodityId();
	}

	@Override
	public int getCheapestProductPrice(String categoryName) {
		return commodityRepository.getCheapestProductPrice(categoryName);
	}

	@Override
	public int getMostExpensiveProductPrice(String categoryName) {
		return commodityRepository.getMostExpensiveProductPrice(categoryName);
	}

	@Override
	public List<CommodityDTO> getAllByCategoryAndProducerNamesInPriceRangeWithOrder(String categoryName, BigDecimal minPrice, BigDecimal maxPrice, String order_type, String... producerNames) {
				
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();
		
		if(producerNames.length != 0) {
		for(String producer : producerNames) {
			list.addAll(objectMapperUtils.mapAll(commodityRepository.getAllByCategoryAndProducerNamesInPriceRange(categoryName, producer, minPrice, maxPrice), CommodityDTO.class));
		}
		}else {
			list.addAll(objectMapperUtils.mapAll(commodityRepository.findAllByCategoryNameInPriceRangeWithOrder(categoryName, minPrice, maxPrice), CommodityDTO.class));
		}
		
		Comparator<CommodityDTO> sorter = new Comparator<CommodityDTO>(){
		
			@Override
			public int compare(CommodityDTO o1, CommodityDTO o2) {
				return (o1.getName().compareTo(o2.getName()));
			}
             
        };
        
		switch(order_type) {
		case "name_asc":
			
			break;
		
		case "name_desc":
			sorter = sorter.reversed();
			break;
		
		case "price_asc":
			sorter = new Comparator<CommodityDTO>() {
				
				@Override
				public int compare(CommodityDTO o1, CommodityDTO o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}
			};
			break;
		
		case "price_desc":
			sorter = new Comparator<CommodityDTO>() {
				
				@Override
				public int compare(CommodityDTO o1, CommodityDTO o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}
			}.reversed();
			break;
			
		default:
			System.out.println("\n\n\n\n ERROR \n\n\n\n");
			return null;
		}
		
		Collections.sort(list, sorter);
		
		PrintLog(list, order_type);
		
		return list;
		
	}
	
	private void PrintLog(List<CommodityDTO> list, String sortType) {
		System.out.println("\n"
				+ "===================================================================================="
				+ "===================================================================================="
				+ "\n\n");
		System.out.println("Sort type: " + sortType);
		
		for( CommodityDTO c : list ) {
			System.out.println(c.toStringLog());
		}
		
		System.out.println("\n\n"
				+ "===================================================================================="
				+ "===================================================================================="
				+ "\n");
	}

}

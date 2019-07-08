package com.malskyi.project.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.http44.api.Response;
import com.malskyi.project.domain.CommodityDTO;
import com.malskyi.project.entity.Commodity;
import com.malskyi.project.repository.CommodityRepository;
import com.malskyi.project.service.CommodityService;

@RestController
@RequestMapping("commodities")
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private CommodityRepository commodityRepository;
		
	@PostMapping
	public ResponseEntity<Void> addCommodity(@RequestBody CommodityDTO commodity){
		commodityService.createCommodity(commodity);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping("update/{image_upd}")
	public ResponseEntity<Void> updateCommodity(@RequestBody CommodityDTO commodity, @PathVariable("image_upd") boolean image_upd){
		commodityService.updateCommodity(commodity, image_upd);
//		commodityService.createCommodity(commodity);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("delete/{commodityId}")
	public ResponseEntity<Void> deleteCommodity(@PathVariable("commodityId") int commodityId){
		commodityService.deleteCommodity(commodityId);
	return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("get-commodity/{commodityId}")
	public ResponseEntity<CommodityDTO> getCommodityById(@PathVariable("commodityId") int commodityId){
		CommodityDTO byCommodityId = commodityService.getById(commodityId);
		return new ResponseEntity<CommodityDTO>(byCommodityId,HttpStatus.OK);
	}
	
	@GetMapping("get-last-created-commodity-id")
	public ResponseEntity<Integer> getLastCreatedCommodityStringId(){
//		String stringId = commodityRepository.getLastCreatedCommodityStringId();
		int id = commodityRepository.getLastCreatedCommodityId();
		return new ResponseEntity<Integer>(id,HttpStatus.OK);
	} 
	
	@GetMapping("category/{categoryId}")
	public ResponseEntity<List<CommodityDTO>> getCommodityByCategoryId(@PathVariable("categoryId") int categoryId){
		List<CommodityDTO> byCategory = commodityService.getAllByCategoryId(categoryId);
		return new ResponseEntity<List<CommodityDTO>>(byCategory,HttpStatus.OK);
	}
	
	@GetMapping("category-name/{name}/price-range/{min}&{max}")
	public ResponseEntity<List<CommodityDTO>> getCommodityByCategoryNameInPriceRange(@PathVariable("name") String categoryName, @PathVariable("min") BigDecimal minPrice, @PathVariable("max") BigDecimal maxPrice){
		List<CommodityDTO> byCategoryWithOrder = commodityService.getAllByCategoryNameInPriceRange(categoryName, minPrice, maxPrice);

		return new ResponseEntity<List<CommodityDTO>>(byCategoryWithOrder,HttpStatus.OK);
	}
	
	@GetMapping("category-name/{name}/orderby={order_type}")
	public ResponseEntity<List<CommodityDTO>> getCommodityByCategoryNameWithOrder(@PathVariable("name") String categoryName, @PathVariable("order_type") String order_type){
		List<CommodityDTO> byCategoryWithOrder = commodityService.getAllByCategoryNameWithOrder(categoryName, order_type);

		return new ResponseEntity<List<CommodityDTO>>(byCategoryWithOrder,HttpStatus.OK);
	}
	
	@GetMapping("category-name/{name}")
	public ResponseEntity<List<CommodityDTO>> getCommodityByCategoryName(@PathVariable("name") String name){
		List<CommodityDTO> byCategory = commodityService.getAllByCategoryName(name);
		return new ResponseEntity<List<CommodityDTO>>(byCategory,HttpStatus.OK);
	}

	@PostMapping("/producers/category-name/{categoryName}/price-range={min}&{max}/orderby={order_type}")
	public ResponseEntity<List<CommodityDTO>> getCommodityByCategoryAndProducersNamesInPriceRangeWithOrder(
			@PathVariable("categoryName") String categoryName,  
			@PathVariable("min") BigDecimal minPrice, 
			@PathVariable("max") BigDecimal maxPrice, 
			@PathVariable("order_type") String order_type,
			@RequestBody String[] producers){
		List<CommodityDTO> byCategoryWithOrder;
		if(producers.length != 0) {
		byCategoryWithOrder = commodityService.getAllByCategoryAndProducerNamesInPriceRangeWithOrder(categoryName, minPrice, maxPrice, order_type, producers);
		}
		else {
		byCategoryWithOrder = commodityService.getAllByCategoryNameInPriceRangeWithOrder(categoryName, minPrice, maxPrice, order_type);
		}
		return new ResponseEntity<List<CommodityDTO>>(byCategoryWithOrder,HttpStatus.OK);
	}
		
	@GetMapping("producer/{producerId}")
	public ResponseEntity<List<CommodityDTO>> getCommodityByProducerId(@PathVariable("producerId") int producerId){
		List<CommodityDTO> byProducer = commodityService.getAllByProducerId(producerId);
		
		return new ResponseEntity<List<CommodityDTO>>(byProducer,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CommodityDTO>> getCommodities(){
		return new ResponseEntity<List<CommodityDTO>>(commodityService.getAll(), HttpStatus.OK);
	}

//	@GetMapping("/pages")
//	public ResponseEntity<List<CommodityDTO>> getCommoditiesByPage(@PageableDefault Pageable pageable){		
//		return new ResponseEntity<List<CommodityDTO>>(commodityService.getAll(pageable), HttpStatus.OK);
//	}
	
//	@GetMapping("/")
//	public ResponseEntity<String> showPage(Model model, @RequestParam(defaultValue="0") int page) {
//		model.addAttribute("data", commodityRepository.findAll(PageRequest.of(page, 10)));
//		
//		return new ResponseEntity<String>("index", HttpStatus.OK);
//	}
	
	@PostMapping("image/{commodityId}")
	public ResponseEntity<Void> uploadImage(
			@PathVariable("commodityId") int commodityId,
			@RequestParam("image") MultipartFile file) {
		
		commodityService.uploadImage(file, commodityId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("image-update/{commodityId}")
	public ResponseEntity<String> uploadImageUpdate(
			@PathVariable("commodityId") int commodityId,
			@RequestParam("image") MultipartFile file) {
		return new ResponseEntity<String>(commodityService.updateImage(file, commodityId), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("image-delete/{commodityId}")
	public ResponseEntity<Void> deleteImage(
			@PathVariable("commodityId") int commodityId) {
		
		commodityService.deleteImage(commodityId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("get-cheapest-price/{categoryName}")
	public ResponseEntity<Integer> getCheapestPrice(@PathVariable("categoryName") String categoryName){
		return new ResponseEntity<Integer>(commodityService.getCheapestProductPrice(categoryName), HttpStatus.OK);
	}
	
	@GetMapping("get-most-expensive-price/{categoryName}")
	public ResponseEntity<Integer> getMostExpensivePrice(@PathVariable("categoryName") String categoryName){
		return new ResponseEntity<Integer>(commodityService.getMostExpensiveProductPrice(categoryName), HttpStatus.OK);
	}
}





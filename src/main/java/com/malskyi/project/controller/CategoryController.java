package com.malskyi.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.malskyi.project.domain.CategoryDTO;
import com.malskyi.project.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	public CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<Void> addCategory(@RequestBody CategoryDTO dto){
		categoryService.createCategory(dto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		return new ResponseEntity<List<CategoryDTO>>(categoryService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("delete")
	public ResponseEntity<Void> deleteCategory(@RequestBody CategoryDTO dto){
		categoryService.deleteCategory(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("get-categoryid/{name}")
	public ResponseEntity<Integer> getCategoryId(@PathVariable("name") String name){
		return new ResponseEntity<Integer>(categoryService.getCategoryIdByName(name), HttpStatus.OK);
	}
	@GetMapping("getCategoryName/{categoryId}")
	public ResponseEntity<String> getCategoryName(@PathVariable("categoryId") int categoryId){
		return new ResponseEntity<String>(categoryService.getNameById(categoryId), HttpStatus.OK);
	}
	
	@GetMapping("get-category-name/{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable("name") String name){
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(name), HttpStatus.OK);
	}
	

	@GetMapping("get-category-id/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") int id){
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryById(id), HttpStatus.OK);
	}
}

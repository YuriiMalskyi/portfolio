package com.malskyi.project.service;

import java.util.List;

import com.malskyi.project.domain.CategoryDTO;

public interface CategoryService {

	void createCategory(CategoryDTO categoryDTO);
	
	List<CategoryDTO> getAll();
	
	void updateCategory(CategoryDTO categoryDTO);
	
	void deleteCategory(CategoryDTO categoryDTO);
	
	int getCategoryIdByName(String name);
	
	String getNameById(int id);
	
	CategoryDTO getCategoryByName(String name);
	
	CategoryDTO getCategoryById(int id);
}

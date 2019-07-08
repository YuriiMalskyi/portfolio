package com.malskyi.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malskyi.project.domain.CategoryDTO;
import com.malskyi.project.entity.Category;
import com.malskyi.project.repository.CategoryRepository;
import com.malskyi.project.service.CategoryService;
import com.malskyi.project.service.utils.ObjectMapperUtils;
import com.malskyi.project.service.utils.StringUtils;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void createCategory(CategoryDTO categoryDTO) {
//		categoryDTO.setCategoryId(stringUtils.generate());
		categoryRepository.save(objectMapperUtils.map(categoryDTO, Category.class));
	}

	@Override
	public List<CategoryDTO> getAll() {
		return objectMapperUtils.mapAll(categoryRepository.findAll(), CategoryDTO.class);
	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		 categoryRepository.save(objectMapperUtils.map(categoryDTO, Category.class));
	}

	@Override
	public void deleteCategory(CategoryDTO categoryDTO) {
		categoryRepository.delete(objectMapperUtils.map(categoryDTO, Category.class));		
	}

	@Override
	public int getCategoryIdByName(String name) {
		return categoryRepository.findByName(name).getId();
	}

	@Override
	public String getNameById(int id) {
		return categoryRepository.findById(id).getName();
//		return categoryRepository.findById(id).get().getName();
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		return objectMapperUtils.map(categoryRepository.findByName(name), CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryById(int id) {
		return objectMapperUtils.map(categoryRepository.findById(id), CategoryDTO.class);
	}

}

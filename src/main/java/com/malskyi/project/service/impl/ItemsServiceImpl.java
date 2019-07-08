package com.malskyi.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.malskyi.project.entity.Items;
import com.malskyi.project.repository.ItemsRepository;
import com.malskyi.project.service.ItemsService;

public class ItemsServiceImpl implements ItemsService{

	@Autowired
	private ItemsRepository itemsRepository;
	
	@Override
	public void createItem(Items item) {
		itemsRepository.save(item);
	}

	@Override
	public Items getItem(int id) {
		return itemsRepository.getOne(id);
	}

}

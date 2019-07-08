package com.malskyi.project.service;

import com.malskyi.project.entity.Items;

public interface ItemsService {

	void createItem(Items item);
	
	Items getItem(int id);
}

package com.malskyi.project.service;

import java.util.List;
import java.util.Set;

import com.malskyi.project.domain.ProducerDTO;

public interface ProducerService {

	void createProducer(ProducerDTO producerDTO);
	
	void updateProducer(ProducerDTO producerDTO);
	
	List<ProducerDTO> getAll();
	
	void deleteProducer(int id);

	void deleteProducerByName(String name);
	
	ProducerDTO getByName(String name);
	
	Set<ProducerDTO> findAllByCategoryName(String categoryName);

	int findCommodityCountOfProducerByProducerAndCategoryNames(String producerName, String categoryName);
}

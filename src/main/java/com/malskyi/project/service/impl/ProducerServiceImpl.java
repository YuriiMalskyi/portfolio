package com.malskyi.project.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malskyi.project.domain.ProducerDTO;
import com.malskyi.project.entity.Producer;
import com.malskyi.project.repository.ProducerRepository;
import com.malskyi.project.service.ProducerService;
import com.malskyi.project.service.utils.ObjectMapperUtils;
import com.malskyi.project.service.utils.StringUtils;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService{

	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private StringUtils stringUtils;
	
	@Override
	public void createProducer(ProducerDTO producerDTO) {
		producerRepository.save(objectMapperUtils.map(producerDTO, Producer.class));
	}

	@Override
	public void updateProducer(ProducerDTO producerDTO) {
//		producerRepository.save(objectMapperUtils.map(producerDTO, Producer.class));
		producerRepository.updateProducer(producerDTO.getId(), producerDTO.getName());
	}

	@Override
	public List<ProducerDTO> getAll() {
		return objectMapperUtils.mapAll(producerRepository.findAll(), ProducerDTO.class);
	}

	@Override
	public ProducerDTO getByName(String name) {
		return objectMapperUtils.map(producerRepository.findByName(name), ProducerDTO.class);
	}

	@Override
	public void deleteProducerByName(String name) {
		producerRepository.deleteById(producerRepository.findByName(name).getId());
	}

	@Override
	public void deleteProducer(int id) {
		producerRepository.deleteById(id);
	}
	
	@Override
	public Set<ProducerDTO> findAllByCategoryName(String categoryName) {
		return objectMapperUtils.mapAllSet(producerRepository.findAllByCategoryName(categoryName), ProducerDTO.class);
	}
	
	@Override	
	public int findCommodityCountOfProducerByProducerAndCategoryNames(String producerName, String categoryName) {
		return producerRepository.findCommodityCountOfProducerByProducerAndCategoryNames(producerName, categoryName);
	}
}

package com.malskyi.project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malskyi.project.domain.ProducerDTO;
import com.malskyi.project.service.ProducerService;

@RestController
@RequestMapping("producers")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
	@PostMapping
	public ResponseEntity<Void> addProducer(@RequestBody ProducerDTO dto){
		producerService.createProducer(dto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
		
	@GetMapping
	public ResponseEntity<List<ProducerDTO>> getProducers(){
		return new ResponseEntity<List<ProducerDTO>>(producerService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("getProducersByCategoryName/{name}")
	public ResponseEntity<Set<ProducerDTO>> getProducersByCategoryName(@PathVariable("name") String name){
		return new ResponseEntity<Set<ProducerDTO>>(producerService.findAllByCategoryName(name), HttpStatus.OK);
	}
	
	@GetMapping("getCommodityCountOfProducerByName/{producerName}-{categoryName}")
	public ResponseEntity<Integer> findCommodityCountOfProducerByProducerAndCategoryNames(@PathVariable("producerName") String producerName, @PathVariable("categoryName") String categoryName){
		return new ResponseEntity<Integer>(producerService.findCommodityCountOfProducerByProducerAndCategoryNames(producerName, categoryName), HttpStatus.OK);
	}
	@GetMapping("getProducerForUpdate/{name}")
	public ResponseEntity<ProducerDTO> getProducerForUpdate(@PathVariable("name") String name){
		ProducerDTO dto = producerService.getByName(name);
		return new ResponseEntity<ProducerDTO>(dto, HttpStatus.OK);
	}
	
	@PostMapping("updateProducer")
	public ResponseEntity<Void> updateProducer(@RequestBody ProducerDTO dto){
		producerService.updateProducer(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("deleteProducerByName/{name}")
	public ResponseEntity<Void> deleteProducerByName(@PathVariable("name") String name){
		producerService.deleteProducerByName(name);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("deleteProducer/{id}")
	public ResponseEntity<Void> deleteProducer(@PathVariable("id") int id){
		producerService.deleteProducer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

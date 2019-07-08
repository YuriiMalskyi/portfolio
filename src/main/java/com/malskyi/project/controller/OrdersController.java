package com.malskyi.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.malskyi.project.domain.OrdersDTO;
import com.malskyi.project.entity.enums.Status;
import com.malskyi.project.repository.OrdersRepository;
import com.malskyi.project.service.ItemsService;
import com.malskyi.project.service.OrdersService;

@RestController
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
//	@Autowired
//	private ItemsService itemsService;
	
//	@Autowired
//	private OrdersRepository ordersRepository;
	
	@PostMapping
	public ResponseEntity<Void> createOrder(@RequestBody OrdersDTO dto){
		ordersService.createOrder(dto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/get-{id}")
	public ResponseEntity<OrdersDTO> getOrder(@PathVariable("id") int id){
		return new ResponseEntity<OrdersDTO>(ordersService.getOrder(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") int id){
		ordersService.deleteOrder(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/get-active-orders")
	public ResponseEntity<List<OrdersDTO>> getActiveOrders(){
		return new ResponseEntity<List<OrdersDTO>>(ordersService.getActiveOrders(), HttpStatus.OK);
	}
	
//	@GetMapping("/get-all-orders")
	@GetMapping
	public ResponseEntity<List<OrdersDTO>> getAllOrders(){
		return new ResponseEntity<List<OrdersDTO>>(ordersService.getAllOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/status-{status}")
	public ResponseEntity<List<OrdersDTO>> getOrdersByStatus(@RequestParam("status") Status st){
		return new ResponseEntity<List<OrdersDTO>>(ordersService.getOrdersByStatus(st), HttpStatus.OK);	
	}
		
}

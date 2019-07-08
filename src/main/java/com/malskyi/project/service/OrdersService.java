package com.malskyi.project.service;

import java.util.List;

import com.malskyi.project.domain.OrdersDTO;
import com.malskyi.project.entity.enums.Status;

public interface OrdersService {

	void createOrder(OrdersDTO ordersDTO);
	
	void deleteOrder(int id);
	
	List<OrdersDTO> getActiveOrders();
	
	List<OrdersDTO> getAllOrders();
	
	List<OrdersDTO> getOrdersByStatus(Status st);
		
	OrdersDTO getOrder(int id);
}

package com.malskyi.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malskyi.project.domain.CommodityDTO;
import com.malskyi.project.domain.OrdersDTO;
import com.malskyi.project.entity.Commodity;
import com.malskyi.project.entity.Items;
import com.malskyi.project.entity.Orders;
import com.malskyi.project.entity.enums.Status;
import com.malskyi.project.repository.ItemsRepository;
import com.malskyi.project.repository.OrdersRepository;
import com.malskyi.project.service.OrdersService;
import com.malskyi.project.service.utils.ObjectMapperUtils;
import com.malskyi.project.service.utils.StringUtils;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private StringUtils stringUtils;

	@Override
	public void createOrder(OrdersDTO ordersDTO) {
		
		System.out.println("createOrder entrance");

//		Orders order = objectMapperUtils.map(ordersDTO, Orders.class);
//		
//		ordersRepository.save(order); // Зберігаємо наше замовлення
//		System.out.println("\n\n\new order id " + order.getId() + "\n\n\n"); // виводимо для перевірки
//		
//		List<Items> products = order.getItems(); // створюємо окрему колекцію наших об'єктів Items
//		
//		for(int i = 0; i < products.size(); i++) { 
//			products.get(i).setOrders(order);	// для кожного ітема додаємо ід ордера
//			itemsRepository.save(products.get(i));	// зберігаємо кожен з ітемів
//		}		

		Orders order = objectMapperUtils.map(ordersDTO, Orders.class);
						

		List<Items> products = order.getItems(); 
		
		for(int i = 0; i < products.size(); i++) { 
			products.get(i).setOrders(order);
		}	
		
		ordersRepository.save(order);	
		
		itemsRepository.saveAll(products);
		
		System.out.println("\n\n\nproducts : \n");
		for(Items it : products) {
			System.out.println("\n\n" +
					it.toStringLog() + "\n" + 
					" [ Id : " + it.getId() + 
					" | Count : " + it.getCount() + 
					" | Item.Commodity.name : " + it.getCommodity().getName() +
					" | Item.Orders.id : " + it.getOrders().getId() +
					" ] " +
					"\n\n");
				
		}	
	}
	
	@Override
	public void deleteOrder(int id) {
		ordersRepository.deleteById(id);
	}
	
	@Override
	public List<OrdersDTO> getActiveOrders() {
		return objectMapperUtils.mapAll(ordersRepository.findAllActiveOrders(), OrdersDTO.class);
	}

	@Override
	public List<OrdersDTO> getAllOrders() {
		return objectMapperUtils.mapAll(ordersRepository.findAll(), OrdersDTO.class);
	}

	@Override
	public List<OrdersDTO> getOrdersByStatus(Status st) {
		return objectMapperUtils.mapAll(ordersRepository.findAllByStatus(st), OrdersDTO.class);
	}

	@Override
	public OrdersDTO getOrder(int id) {
			return objectMapperUtils.map(ordersRepository.getOne(id), OrdersDTO.class);
	}
	
	
}

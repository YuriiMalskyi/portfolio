package com.malskyi.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.malskyi.project.entity.Orders;
import com.malskyi.project.entity.enums.Status;


//@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	
	@Query("Select o from Orders o where o.status is not com.malskyi.project.entity.enums.Status.COMPLETED")
	List<Orders> findAllActiveOrders();
	
	@Query("select o from Orders o where o.status = ?1")
	List<Orders> findAllByStatus(Status st);
	
	
}

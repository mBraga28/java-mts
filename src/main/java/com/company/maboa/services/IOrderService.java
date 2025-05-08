package com.company.maboa.services;

import java.util.List;

import com.company.maboa.dtos.OrderDTO;
import com.company.maboa.dtos.SalesStatisticsDTO;

public interface IOrderService {
	
	OrderDTO findById(Long id);
	List<OrderDTO> findAll();
	OrderDTO createOrder(OrderDTO orderDto);
	OrderDTO updateOrderStatus(Long id, OrderDTO orderDTO);
	// OrderDTO deleteOrder(Long id);
	SalesStatisticsDTO getSalesStatistics(); // New method for sales statistics
	List<OrderDTO> findAllFiltered(); // New method for filtered orders

}

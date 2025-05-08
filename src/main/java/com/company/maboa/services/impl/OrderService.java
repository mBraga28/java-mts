package com.company.maboa.services.impl;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.maboa.dtos.OrderDTO;
import com.company.maboa.dtos.OrderItemDTO;
import com.company.maboa.dtos.SalesStatisticsDTO;
import com.company.maboa.entities.Order;
import com.company.maboa.entities.OrderItem;
import com.company.maboa.entities.OrderStatus;
import com.company.maboa.entities.Product;
import com.company.maboa.repositories.OrderItemRepository;
import com.company.maboa.repositories.OrderRepository;
import com.company.maboa.repositories.ProductRepository;
import com.company.maboa.services.IOrderService;
import com.company.maboa.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService implements IOrderService {
	
	private final OrderRepository orderRepository;
	
	private final ProductRepository productRepository;
	
	private final OrderItemRepository orderItemRepository;
	
	public OrderService(OrderRepository orderRepository, 
						ProductRepository productRepository, 
						OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Entity not found") 
				);
		return new OrderDTO(order);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream().map(OrderDTO::new).toList();
	}

	@Override
	@Transactional
	public OrderDTO createOrder(OrderDTO orderDto) {
		
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		for (OrderItemDTO itemDto: orderDto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			
			// Update the stock
			Integer newStock = product.getStock() - itemDto.getQuantity();
	        if (newStock < 0) {
	            throw new IllegalArgumentException("Not enough stock for product: " + product.getNameProduct());
	        }
	        product.setStock(newStock);
	        productRepository.save(product);
			
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), itemDto.getPrice());
			order.getItems().add(item);
		}
		
		orderRepository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}

	@Override
	@Transactional
	public OrderDTO updateOrderStatus(Long id, OrderDTO orderDto) {
		Order order = orderRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Entity not found") 
				);
		order.setStatus(orderDto.getStatus());
		if (orderDto.getStatus() == OrderStatus.CANCELED) {
			for (OrderItem item : order.getItems()) {
				Product product = productRepository.getReferenceById(item.getProduct().getId());
				product.setStock(product.getStock() + item.getQuantity());
				productRepository.save(product);
			}
		} else if (orderDto.getStatus() == OrderStatus.DELIVERED) {
			order.setMoment(Instant.now());
		} else if (orderDto.getStatus() == OrderStatus.PAID) {
			order.setMoment(Instant.now());
		} else if (orderDto.getStatus() == OrderStatus.SHIPPED) {
			order.setMoment(Instant.now());
		}
		order = orderRepository.save(order);
		return new OrderDTO(order);
	}


	// @Override
	// @Transactional
	// public OrderDTO setDelivered(Long id) {
	// 	Order order = orderRepository.findById(id).orElseThrow(
	// 			() -> new ResourceNotFoundException("Entity not found") 
	// 			);
	// 	order.setStatus(OrderStatus.DELIVERED);
	// 	order = orderRepository.save(order);
	// 	return new OrderDTO(order);
	// }

	// @Override
	// @Transactional
	// public OrderDTO setCanceled(Long id) {		
	// 	Order order = orderRepository.findById(id).orElseThrow(
	// 			() -> new ResourceNotFoundException("Entity not found") 
	// 			);
	// 	order.setStatus(OrderStatus.CANCELED);
	// 	order = orderRepository.save(order);
	// 	return new OrderDTO(order);
	// }

	public SalesStatisticsDTO getSalesStatistics() {
    List<Order> orders = orderRepository.findByStatusNotIn(List.of(OrderStatus.WAITING_PAYMENT, OrderStatus.CANCELED)); // ou PAID, conforme seu fluxo

    Integer totalSales = orders.size();
    Double totalRevenue = orders.stream()
        .flatMap(o -> o.getItems().stream())
        .mapToDouble(item -> item.getPrice() * item.getQuantity())
        .sum();

    // Exemplo: produtos mais vendidos
    Map<String, Integer> productSales = new HashMap<>();
    for (Order order : orders) {
        for (OrderItem item : order.getItems()) {
            String productName = item.getProduct().getNameProduct();
            productSales.put(productName, productSales.getOrDefault(productName, 0) + item.getQuantity());
        }
    }

    	return new SalesStatisticsDTO(totalSales, totalRevenue, productSales);
	}

	@Transactional(readOnly = true)
	public List<OrderDTO> findAllFiltered() {
	    List<Order> orders = orderRepository.findByStatusNotIn(
	        List.of(OrderStatus.WAITING_PAYMENT, OrderStatus.CANCELED)
	    );
	    return orders.stream().map(OrderDTO::new).toList();
	}
	
}

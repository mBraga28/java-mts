package com.company.maboa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.maboa.dtos.OrderDTO;
import com.company.maboa.dtos.SalesStatisticsDTO;
import com.company.maboa.services.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	public final IOrderService orderService;

	public OrderController(IOrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDto) {
		orderDto = orderService.createOrder(orderDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDto.getId()).toUri();
		return ResponseEntity.created(uri).body(orderDto);
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
		OrderDTO orderDto = orderService.findById(id);
		return ResponseEntity.ok(orderDto);
	}

	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
	    List<OrderDTO> list = orderService.findAll();
	    return ResponseEntity.ok(list);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
	    OrderDTO dto = orderService.updateOrderStatus(id, orderDTO);		
	    return ResponseEntity.ok(dto);
	}

	@GetMapping("/sales-statistics")
    public ResponseEntity<SalesStatisticsDTO> getSalesStatistics() {
        SalesStatisticsDTO stats = orderService.getSalesStatistics();
        return ResponseEntity.ok(stats);
    }

	@GetMapping("/filtered")
	public ResponseEntity<List<OrderDTO>> findAllFiltered() {
		List<OrderDTO> orders = orderService.findAllFiltered();
		return ResponseEntity.ok(orders);
	}
}

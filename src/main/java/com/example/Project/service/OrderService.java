package com.example.Project.service;

import com.example.Project.model.Order;
import com.example.Project.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        return orderOpt.orElseThrow(() -> new RuntimeException("Order with ID " + orderId + " not found."));
    }

    public Order createOrder(Order order) {
        // Additional business logic or validations can be performed here if needed
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);

        // Update the existing order with the new data
        existingOrder.setCustomerName(updatedOrder.getCustomerName());
        existingOrder.setOrderDate(updatedOrder.getOrderDate());
        existingOrder.setShippingAddress(updatedOrder.getShippingAddress());
        existingOrder.setTotal(updatedOrder.getTotal());

        // Save the updated order
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long orderId) {
        Order existingOrder = getOrderById(orderId);
        orderRepository.delete(existingOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

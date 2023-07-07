package com.example.Project;

import com.example.Project.model.Order;
import com.example.Project.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveOrder() {
        // Arrange
        Order order = new Order("Shatt Vang", LocalDate.now(), "3 North Street", 10.0);

        // Act
        Order savedOrder = orderRepository.save(order);

        // Assert
        assertNotNull(savedOrder.getId());
        assertEquals(order.getCustomerName(), savedOrder.getCustomerName());
        assertEquals(order.getOrderDate(), savedOrder.getOrderDate());
        assertEquals(order.getShippingAddress(), savedOrder.getShippingAddress());
        assertEquals(order.getTotal(), savedOrder.getTotal());
    }
}

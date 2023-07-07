package com.example.Project;

import com.example.Project.controller.OrderController;
import com.example.Project.model.Order;
import com.example.Project.repo.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrder_ReturnsOrder_WhenOrderExists() {
        // Arrange
        Long orderId = 1L;
        Order expectedOrder = new Order("Shatt Vang", LocalDate.now(), "3 North Street", 10.0);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Act
        ResponseEntity<Order> response = orderController.getOrder(orderId.toString());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());
    }

    @Test
    public void testGetOrder_ReturnsNotFound_WhenOrderDoesNotExist() {
        // Arrange
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Order> response = orderController.getOrder(orderId.toString());

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null);
    }

    @Test
    public void testUpdateOrder_ReturnsUpdatedOrder() {
        // Arrange
        Long orderId = 1L;
        Order existingOrder = new Order("Shatt Vang", LocalDate.now(), "3 North Street", 10.0);
        Order updatedOrder = new Order("Bryon Vang", LocalDate.now(), "4 North Street", 10.0);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(existingOrder);

        // Act
        ResponseEntity<Object> response = orderController.updateOrder(orderId.toString(), updatedOrder);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingOrder, response.getBody());
    }

    @Test
    public void testCreateOrder_ReturnsCreatedOrder() {
        // Arrange
        Order order = new Order("Shatt Vang", LocalDate.now(), "3 North Street", 10.0);
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        ResponseEntity<Object> response = orderController.createOrder(order, mock(BindingResult.class));

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    public void testCreateOrder_ReturnsBadRequest_WhenValidationErrorsExist() {
        // Arrange
        Order order = new Order("", LocalDate.now(), "", -1.0);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<Object> response = orderController.createOrder(order, bindingResult);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        assertTrue(((List<?>) response.getBody()).isEmpty());
    }

    @Test
    public void testDeleteOrder_DeletesOrder() {

        Long orderId = 1L;
        Order existingOrder = new Order("Shatt Vang", LocalDate.now(), "3 North Street", 10.0);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

        // Act
        ResponseEntity<String> response = orderController.deleteOrder(orderId.toString());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order with ID " + orderId + " has been deleted.", response.getBody());
        verify(orderRepository, times(1)).delete(existingOrder);
    }

    @Test
    public void testGetAllOrders_ReturnsAllOrders() {
        // Arrange
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Shatt Vang", LocalDate.now(), "3 North Street", 10.0));
        orders.add(new Order("Byron Vang", LocalDate.now(), "4 North Street", 10.0));
        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        ResponseEntity<List<Order>> response = orderController.getAllOrders();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    public void testUpdateOrder_ThrowsException_WhenOrderDoesNotExist() {
        // Arrange
        Long orderId = 1L;
        Order updatedOrder = new Order("Byron Vang", LocalDate.now(), "4 North Street", 10.0);
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            orderController.updateOrder(orderId.toString(), updatedOrder);
        });
    }

    @Test
    public void testDeleteOrder_ThrowsException_WhenOrderDoesNotExist() {
        // Arrange
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            orderController.deleteOrder(orderId.toString());
        });
    }
}


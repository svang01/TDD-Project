package com.example.Project;
import com.example.Project.model.Order;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Long id = 1L;
        String customerName = "Shatt Vang";
        LocalDate orderDate = LocalDate.now();
        String shippingAddress = "3 north street";
        Double total = 300.0;

        // Act
        Order order = new Order();
        order.setId(id);
        order.setCustomerName(customerName);
        order.setOrderDate(orderDate);
        order.setShippingAddress(shippingAddress);
        order.setTotal(total);

        // Assert
        assertEquals(id, order.getId());
        assertEquals(customerName, order.getCustomerName());
        assertEquals(orderDate, order.getOrderDate());
        assertEquals(shippingAddress, order.getShippingAddress());
        assertEquals(total, order.getTotal());
    }

    @Test
    public void testConstructor() {
        // Arrange
        String customerName = "S Vang";
        LocalDate orderDate = LocalDate.now();
        String shippingAddress = "3 north street";
        Double total = 10.0;

        // Act
        Order order = new Order(customerName, orderDate, shippingAddress, total);

        // Assert
        assertNull(order.getId());
        assertEquals(customerName, order.getCustomerName());
        assertEquals(orderDate, order.getOrderDate());
        assertEquals(shippingAddress, order.getShippingAddress());
        assertEquals(total, order.getTotal());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        Order order1 = new Order("Shatt Vang", LocalDate.now(), "3 north street", 10.0);
        Order order2 = new Order("Shatt Vang", LocalDate.now(), "3 north street", 10.0);
        Order order3 = new Order("Bryon Vang", LocalDate.now(), "4 north street", 15.0);

        // Assert
        assertEquals(order1, order2);
        assertNotEquals(order1, order3);
        assertEquals(order1.hashCode(), order2.hashCode());
        assertNotEquals(order1.hashCode(), order3.hashCode());
    }
}

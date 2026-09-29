import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void addOrder_orderListSizeShouldBe1_whenAdded1() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order order = new Order("1", new ArrayList<>());
        //When
        int actual = orderRepo.addOrder(order);
        //Then
        assertEquals(1, actual);
    }
    @Test
    void addOrder_orderLSizeShouldBe2_whenAdded2() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        Order orderB = new Order("2", new ArrayList<>());
        //When
        orderRepo.addOrder(orderA);
        int actual = orderRepo.addOrder(orderB);

        //Then
        assertEquals(2, actual);
    }

    @Test
    void addOrder_orderLSizeShouldBe1_whenAddedDuplicate() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        Order orderB = new Order("1", new ArrayList<>());
        //When
        orderRepo.addOrder(orderA);
        int actual = orderRepo.addOrder(orderB);

        //Then
        assertEquals(1, actual);
    }

    @Test
    void removeOrder_listSizeShouldBe0_whenRemoved1From1() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order order = new Order("1", new ArrayList<>());
        orderRepo.addOrder(order);
        //When
        int actual = orderRepo.removeOrder(order.id());
        //Then
        assertEquals(0, actual);
    }

    @Test
    void removeOrder_listSizeShouldBe1_whenRemoved1From2() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        Order orderB = new Order("2", new ArrayList<>());
        orderRepo.addOrder(orderA);
        orderRepo.addOrder(orderB);
        //When
        int actual = orderRepo.removeOrder(orderB.id());
        //Then
        assertEquals(1, actual);
    }

    @Test
    void removeOrder_listSizeShouldBe1_whenRemoved1From2SecondTime() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        Order orderB = new Order("2", new ArrayList<>());
        orderRepo.addOrder(orderA);
        orderRepo.addOrder(orderB);
        //When
        orderRepo.removeOrder(orderB.id());
        int actual = orderRepo.removeOrder(orderB.id());
        //Then
        assertEquals(1, actual);
    }

    @Test
    void retrieveOrder_shouldBeMilk_whenAskedId1AndOnlyId1IsInList() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        orderRepo.addOrder(orderA);
        //When
        Order actualOrder = orderRepo.retrieveOrder("1");
        //Then
        assertEquals("1", actualOrder.id());
    }

    @Test
    void retrieveOrder_shouldBe1_whenAskedId1AndManyIncludingId1AreInList() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        Order orderB = new Order("2", new ArrayList<>());
        Order orderC = new Order("3", new ArrayList<>());
        orderRepo.addOrder(orderA);
        orderRepo.addOrder(orderB);
        orderRepo.addOrder(orderC);
        //When
        Order actualOrder = orderRepo.retrieveOrder("2");
        //Then
        assertEquals("2", actualOrder.id());
    }

    @Test
    void retrieveOrder_shouldBeNull_whenAskedId2AndNoId2InList() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        Order orderC = new Order("3", new ArrayList<>());
        orderRepo.addOrder(orderA);
        orderRepo.addOrder(orderC);
        //When
        Order actualOrder = orderRepo.retrieveOrder("2");
        //Then
        assertNull(actualOrder);
    }

    @Test
    void retrieveOrder_shouldBeNull_whenAskedId2AndListIsEmpty() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        //When
        Order actualOrder = orderRepo.retrieveOrder("2");
        //Then
        assertNull(actualOrder);
    }

    @Test
    void retrieveAllOrders_shouldBe1_when1inList() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order order = new Order("1", new ArrayList<>());
        orderRepo.addOrder(order);
        //When
        Set<Order> orders = orderRepo.retrieveAllOrders();
        //Then
        assertEquals(1, orders.size());
    }

    @Test
    void retrieveAllOrders_shouldBe2_when2inList() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        Order orderA = new Order("1", new ArrayList<>());
        Order orderB = new Order("2", new ArrayList<>());
        orderRepo.addOrder(orderA);
        orderRepo.addOrder(orderB);
        //When
        Set<Order> orders = orderRepo.retrieveAllOrders();
        //Then
        assertEquals(2, orders.size());
    }

    @Test
    void retrieveAllOrders_shouldBe0_when0inList() {
        //Given
        OrderListRepo orderRepo = new OrderListRepo();
        //When
        Set<Order> orders = orderRepo.retrieveAllOrders();
        //Then
        assertEquals(0, orders.size());
    }
}
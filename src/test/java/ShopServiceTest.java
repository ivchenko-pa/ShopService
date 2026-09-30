import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void placeOrder_shouldBe1productInOrder_whenOrdered1() {
        //Given
        Product product = new Product("1", "milk");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(product);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        //When
        String orderId = shopService.placeOrder(new String[]{"1"});
        //Then
        Order actualOrder = orderRepo.retrieveOrder(orderId);
        int actualProductsAmountInOrder = actualOrder.orderedProducts().size();
        assertEquals(1, actualProductsAmountInOrder);
    }

    @Test
    void placeOrder_shouldBe2productInOrder_whenOrdered2() {
        //Given
        Product productA = new Product("1", "milk");
        Product productB = new Product("2", "apple");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        //When
        String orderId = shopService.placeOrder(new String[]{"1", "2"});
        //Then
        Order actualOrder = orderRepo.retrieveOrder(orderId);
        int actualProductsAmountInOrder = actualOrder.orderedProducts().size();
        assertEquals(2, actualProductsAmountInOrder);
    }

    @Test
    void placeOrder_shouldBe1productInOrder_whenOrdered1availableAnd1Unavaiable() {
        //Given
        Product product = new Product("1", "milk");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(product);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        //When
        String orderId = shopService.placeOrder(new String[]{"1", "2"});
        //Then
        Order actualOrder = orderRepo.retrieveOrder(orderId);
        int actualProductsAmountInOrder = actualOrder.orderedProducts().size();
        assertEquals(1, actualProductsAmountInOrder);
    }

    @Test
    void placeOrder_shouldBePlaced1Order_when2ProductsOrdered() {
        //Given
        Product productA = new Product("1", "milk");
        Product productB = new Product("2", "apple");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        //When
        shopService.placeOrder(new String[]{"1`", "2"});
        //Then
        int actualOrdersAmount = orderRepo.retrieveAllOrders().size();
        assertEquals(1, actualOrdersAmount);
    }

    @Test
    void placeOrder_shouldBePlaced0Orders_whenOnlyUnavailableProductOrdered() {
        //Given
        Product product = new Product("1", "milk");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(product);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        //When
        String orderId = shopService.placeOrder(new String[]{"2"});
        //Then
        int actualOrdersAmount = orderRepo.retrieveAllOrders().size();
        assertEquals(0, actualOrdersAmount);
    }

    @Test
    void placeOrder_verifyProductsInstanceInOrderEqualsOrderedOne() {
        //Given
        Product product = new Product("1", "milk");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(product);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        //When
        String orderId = shopService.placeOrder(new String[]{"1"});
        //Then
        Order actualOrder = orderRepo.retrieveOrder(orderId);
        Product actualProductInOrder = actualOrder.orderedProducts().get(0);
        assertEquals(product, actualProductInOrder);
    }

    @Test
    void getOrder_shouldBeCorrectOrderReturned_when1OrderInRepo() {
        //Given
        Product product = new Product("1", "milk");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(product);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        //When
        String orderId = shopService.placeOrder(new String[]{"1"});
        //Then
        Order actualOrder = orderRepo.retrieveOrder(orderId);
        String actualOrderId = actualOrder.id();
        assertEquals("Order_1", actualOrderId);
    }

    @Test
    void getOrder_shouldBeCorrectOrderReturned_when3OrderInRepo() {
        //Given
        Product productA = new Product("1", "milk");
        Product productB = new Product("2", "apple");
        Product productC = new Product("3", "water");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);
        productRepo.addProduct(productC);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        String orderIdA = shopService.placeOrder(new String[]{"1"});
        String orderIdBC = shopService.placeOrder(new String[]{"2","3"});
        String orderIdC = shopService.placeOrder(new String[]{"3"});
        //When
        Order actualOrder = orderRepo.retrieveOrder(orderIdBC);
        //Then
        String actualOrderId = actualOrder.id();
        assertEquals("Order_2", actualOrderId);
    }

    @Test
    void getOrder_shouldBeNull_whenInvalidOrderIdRequested() {
        //Given
        Product product = new Product("1", "milk");
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(product);
        OrderRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        shopService.placeOrder(new String[]{"1"});
        Order actualOrder = orderRepo.retrieveOrder("ABC");
        //Then
        assertNull(actualOrder);
    }
}
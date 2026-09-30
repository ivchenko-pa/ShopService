import java.math.BigDecimal;
import java.util.*;

public class ShopService {

    private int ordersCounter = 0;
    ProductRepo productRepo;
    OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public String placeOrder(String [] ids){
        if (Objects.isNull(ids) || ids.length == 0 || ids[0].isEmpty()){
            System.out.println("Order cancelled: No id's provided to place in the order");
            return null;
        }
        List<Product> listOfProducts = new ArrayList<>();
        for (String id : ids){
            Product product = productRepo.retrieveProduct(id);
            if(Objects.isNull(product)){
                System.out.printf("The product with ID: %s is out of stock.\n", id);
            }else{
                listOfProducts.add(product);
            }
        }

        if (listOfProducts.size() > 0){
            ordersCounter++;
            String orderId = "ID_" + String.valueOf(ordersCounter);
            orderRepo.addOrder(new Order(orderId, listOfProducts));
            return orderId;
        }
        return null;
    }

    public Order getOrder(String id){
        return orderRepo.retrieveOrder(id);
    }

    public List<String> listOrders(){
        List<String> orderIds = new ArrayList<>();
        orderRepo.retrieveAllOrders().forEach(order-> orderIds.add(order.id()));
        return orderIds;
    }

    public List<String> listProducts(){
        List<String> products = new ArrayList<>();
        productRepo.retrieveAllProducts().forEach(product-> products.add(product.toString()));
        return products;
    }

    public String getReceipt(String orderId){
        Order order = getOrder(orderId);

        Map<String, Integer> orderedProductsMap = new HashMap<>();

        for(Product product : order.orderedProducts()){
            if(orderedProductsMap.containsKey(product.id())){
                int actualCount = orderedProductsMap.get(product.id());
                actualCount++;
                orderedProductsMap.put(product.id(), actualCount);
            }else {
                orderedProductsMap.put(product.id(), 1);
            }
        }

        String receiptHeader = "*".repeat(35) + "\n" +
                "*".repeat(10) + String.format("  ORDER: %-6s", orderId) + "*".repeat(10)+"\n" +
                "*".repeat(35) + "\n";

        List<BigDecimal> totalAmounts = new ArrayList<>();
        StringBuilder receiptBody = new StringBuilder();
        orderedProductsMap.forEach((productId, count) -> {
            Product product = productRepo.retrieveProduct(productId);
            receiptBody.append("*" + String.format(" %-25s %-6s", product.name(), product.price()) + "*\n") ;
            receiptBody.append("*" + " ".repeat(27) + String.format("x%-4d ", count) + "*\n") ;
            totalAmounts.add(new BigDecimal(product.price()).multiply(new BigDecimal(count)));
        });

        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (BigDecimal amount : totalAmounts){
            totalPrice = totalPrice.add(amount);
        }

        String receiptFooter = "*" + " ".repeat(33) + "*\n" +
                "*" + String.format("%20s %6s EURO ", "Total price:", totalPrice) + "*\n" +
                "*".repeat(35) + "\n" +
                "*".repeat(35);

        return receiptHeader + receiptBody + receiptFooter;
    }

}

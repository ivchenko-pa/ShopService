import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            System.out.println("Order cancelled: No id's provided to place the order");
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
            String orderId = "Order_" + String.valueOf(ordersCounter);
            orderRepo.addOrder(new Order(orderId, listOfProducts));
            return orderId;
        }
        return null;
    }

    public Order getOrder(String id){
        return orderRepo.retrieveOrder(id);
    }

    public void listOrders(){
        orderRepo.retrieveAllOrders().forEach(order-> System.out.println(order));
    }

}

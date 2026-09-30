import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopService {

    private static int ordersCounter = 0;
    ProductRepo productRepo;
    OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void addProductToRepo(Product product){
        productRepo.addProduct(product);
    }

    public void placeOrder(String [] ids){
        if (Objects.isNull(ids) || ids.length == 0 || ids[0].isEmpty()){
            System.out.println("Order cancelled: No id's provided to place the order");
            return;
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
            orderRepo.addOrder(new Order("Order_" + String.valueOf(ordersCounter), listOfProducts));
        }
    }

    public Order getOrder(String id){
        return orderRepo.retrieveOrder(id);
    }

    public void listOrders(){
        orderRepo.retrieveAllOrders().forEach(order-> System.out.println(order));
    }

}
